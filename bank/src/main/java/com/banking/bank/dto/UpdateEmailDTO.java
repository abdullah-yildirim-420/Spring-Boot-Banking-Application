package com.banking.bank.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateEmailDTO {

    @NotBlank(message = "New email cannot be blank")
    @Email(message = "Please provide a valid email address")
    private final String newEmail;

    @NotBlank(message = "Current password cannot be blank")
    private final String currentPassword;
}
