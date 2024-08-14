package com.banking.bank.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionRequestDTO {

    @NotBlank(message = "Account ID cannot be blank")
    private final Long accountId;

    @NotBlank(message = "Username cannot be blank")
    @Size(min = 5, max = 20, message = "Username must be between 5 and 20 characters")
    private final String username;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
    private final String password;

    @NotBlank(message = "Amount cannot be blank")
    @Positive(message = "Amount must be positive")
    private final BigDecimal amount;
}
