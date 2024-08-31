package com.ateaf.ecommerce.payment;

import com.ateaf.ecommerce.customer.CustomerResponse;
import com.ateaf.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
