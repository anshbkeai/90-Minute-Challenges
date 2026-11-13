package com.challenge.payflow.core;

import java.math.BigDecimal;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;

public record CreatePayment(
    @NotBlank String merchantId , 
    @Digits(integer = 17, fraction = 2) BigDecimal amount,
    @NotBlank String currency
) {

}
