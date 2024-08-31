package com.ateaf.ecommerce.kafka;

import java.math.BigDecimal;
import java.util.List;

import com.ateaf.ecommerce.customer.CustomerResponse;
import com.ateaf.ecommerce.order.PaymentMethod;
import com.ateaf.ecommerce.product.responses.PurchaseResponse;

public record OrderConfirmation(
    String orderReference,
    BigDecimal totalAmount,
    PaymentMethod payementMethod,
    CustomerResponse customer,
    List<PurchaseResponse> products
) {

}
