package com.banking.bank.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdatePasswordDTO {

    @NotBlank(message = "New password cannot be blank.")
    @Size(min = 8, max = 100, message = "Password size must be between 8 and 100 characters.")
    private final String newPassword;

    @NotBlank(message = "Current password cannot be blank.")
    private final String currentPassword;
}
