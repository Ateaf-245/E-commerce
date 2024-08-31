package com.ateaf.ecommerce.customer;

import org.springframework.validation.annotation.Validated;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Validated
@Embeddable
public class Address {
    
    private String street;
    private String houseNumber;
    private String zipCode;

}
