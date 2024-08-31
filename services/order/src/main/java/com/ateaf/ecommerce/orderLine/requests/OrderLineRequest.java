package com.ateaf.ecommerce.orderLine.requests;

public record OrderLineRequest(
    Integer Id,
    Integer orderId,
    Integer productId,
    double quantity
) {

}
