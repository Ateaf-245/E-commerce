package com.ateaf.ecommerce.order;

import org.springframework.stereotype.Service;

import com.ateaf.ecommerce.order.requests.OrderRequest;
import com.ateaf.ecommerce.order.responses.OrderResponse;

@Service
public class OrderMapper {

    public Order toOrder(OrderRequest request){
        return Order
            .builder()
            .id(request.Id())
            .reference(request.reference())
            .totalAmount(request.amount())
            .paymentMethod(request.payementMethod())
            .customerId(request.customerId())
            .build();
    }

    public OrderResponse fromOrder(Order order){
        return new OrderResponse(
                order.getId(), 
                order.getReference(), 
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId());
    }

}
