package com.banking.bank.dto;

import com.banking.bank.validator.Adult;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserCreateDTO {

    @NotBlank
    @Size(min = 2, max = 50)
    private final String firstName;
    @NotBlank
    @Size(min = 2, max = 50)
    private final String lastName;
    @Past
    @Adult
    private final LocalDate dateOfBirth;
    @NotBlank
    @Size(min = 5, max = 20)
    private final String  username;
    @NotBlank
    @Size(min = 8, max = 100)
    private final String password;
    @NotBlank
    @Email
    private final String email;
    @NotBlank
    @Size(min = 7, max = 15)
    @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "Phone number must be between 7 and 15 digits and may start with a '+'")
    private final String phone;



}
