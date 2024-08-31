package com.ateaf.ecommerce.order;

import java.util.List;
import java.util.stream.Collectors;

import com.ateaf.ecommerce.payment.PaymentClient;
import com.ateaf.ecommerce.payment.PaymentRequest;
import com.ateaf.ecommerce.customer.CustomerClient;
import com.ateaf.ecommerce.exception.BusinessException;
import com.ateaf.ecommerce.kafka.OrderConfirmation;
import com.ateaf.ecommerce.kafka.OrderProducer;
import com.ateaf.ecommerce.order.requests.OrderRequest;
import com.ateaf.ecommerce.order.responses.OrderResponse;
import com.ateaf.ecommerce.orderLine.OrderLineService;
import com.ateaf.ecommerce.orderLine.requests.OrderLineRequest;
import com.ateaf.ecommerce.product.ProductClient;
import com.ateaf.ecommerce.product.requests.PurchaseRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepositry repository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;

    private final OrderProducer orderProducer;

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final PaymentClient paymentClient;
    
    public Integer createOrder(OrderRequest request) {
        //check the customer  --> customer-ms
        var customer = customerClient.findCustomerById(request.customerId())
                    .orElseThrow(()-> new BusinessException("Cannot create order:: No Customer exists with Id "+ request.customerId()));
        log.info("Customer details fetched from Customer Service :: {}",customer);

        //purchase the products --> product-ms
        var purchasedProducts =  productClient.purchaseProducts(request.produts());
        log.info(" Product availability checked");
        // persist order
        log.info("Saving the order in the database :: {}", request);
        var order = repository.save(mapper.toOrder(request));

        for(PurchaseRequest purchaseRequest: request.produts()){
            orderLineService.saveOrderLine(
                new OrderLineRequest(
                    null,
                    order.getId(),
                    purchaseRequest.productId(),
                    purchaseRequest.quantity()
                )  
            );
        }
        log.info("Making Payment request");
        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.payementMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);

        //send the order confirmation --notification-ms(kafka)
        orderProducer.sendOrderConfirmation(
            new OrderConfirmation(
                request.reference(),
                request.amount(),
                request.payementMethod(),
                customer,
                purchasedProducts
            )
        );

        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return repository.findAll().stream()
                    .map(mapper::fromOrder)
                    .collect(Collectors.toList());
    }

    public OrderResponse findByOrderId(Integer orderId) {
        return repository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(()-> new BusinessException(
                    String.format("No order found with the provided ID : %d", orderId))
                );
    }

}
