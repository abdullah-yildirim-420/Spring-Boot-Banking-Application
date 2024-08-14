package com.banking.bank.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUsernameDTO {

    @NotBlank(message = "Username cannot be blank")
    @Size(min = 5, max = 20, message = "Username size must be between 5 and 20 characters")
    private final String newUsername;

    @NotBlank(message = "Current password cannot be blank")
    private final String currentPassword;

}
