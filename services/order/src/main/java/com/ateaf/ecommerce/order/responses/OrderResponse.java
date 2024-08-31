package com.ateaf.ecommerce.order.responses;

import java.math.BigDecimal;

import com.ateaf.ecommerce.order.PaymentMethod;

public record OrderResponse(
    Integer id,
    String reference,
    BigDecimal amount,
    PaymentMethod payementMethod,
    String customerId
) {

}
