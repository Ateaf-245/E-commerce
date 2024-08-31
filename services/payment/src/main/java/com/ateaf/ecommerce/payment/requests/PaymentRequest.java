package com.ateaf.ecommerce.payment.requests;

import com.ateaf.ecommerce.payment.Customer;
import com.ateaf.ecommerce.payment.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        Customer customer
) {
}
