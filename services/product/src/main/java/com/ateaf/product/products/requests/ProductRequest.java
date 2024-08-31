package com.ateaf.product.products.requests;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public record ProductRequest(
    Integer id,

    @NotNull(message = "product name is required")
    String name,

    @NotNull(message = "product description is required")
    String description,

    @NotNull(message = "available Quantity should be positive")
    double availableQuantity,

    @NotNull(message = "price should be positive")
    BigDecimal price,

    @NotNull(message = "product category is required")
    Integer categoryId
) {
}
