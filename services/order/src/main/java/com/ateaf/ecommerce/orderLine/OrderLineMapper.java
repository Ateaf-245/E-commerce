package com.ateaf.ecommerce.orderLine;

import org.springframework.stereotype.Service;

import com.ateaf.ecommerce.order.Order;
import com.ateaf.ecommerce.orderLine.requests.OrderLineRequest;
import com.ateaf.ecommerce.orderLine.responses.OrderLineResponse;

@Service
public class OrderLineMapper {

    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
            .id(request.Id())
            .quantity(request.quantity())
            .order(
                Order.builder()
                        .id(request.orderId())
                        .build()   
            )
            .product_id(request.productId())
            .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse( orderLine.getId(), orderLine.getQuantity());
    }
    
}
