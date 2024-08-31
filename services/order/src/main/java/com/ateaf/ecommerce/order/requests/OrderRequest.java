package com.ateaf.ecommerce.order.requests;

import java.math.BigDecimal;
import java.util.List;

import com.ateaf.ecommerce.order.PaymentMethod;
import com.ateaf.ecommerce.product.requests.PurchaseRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderRequest(
    Integer Id,
    String reference,
    @Positive(message = "Order amount should be positive")
    BigDecimal amount,
    @NotNull(message = "Payement method should be precised")
    PaymentMethod payementMethod,
    @NotNull(message = "Customer should be precised")
    @NotEmpty(message = "Customer should be precised")
    @NotBlank(message = "Customer should be precised")
    String customerId,
    @NotEmpty(message = "You should at least purchase one produt")
    List<PurchaseRequest> produts
) {

}
