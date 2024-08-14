package com.banking.bank.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AccountResponseDTO {

    private final Long id;
    private final Long userId;
    private final String firstName;
    private final String lastName;
    private final BigDecimal balance;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;


}
