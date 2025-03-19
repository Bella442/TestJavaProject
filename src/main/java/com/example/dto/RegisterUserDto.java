package com.example.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterUserDto {
    private final String requiredFieldMessage =  "This field is required";

    @Valid
    @NotBlank(message = requiredFieldMessage)
    @Email(message = "Should be a valid email address")
    private String email;

    @Valid
    @NotBlank(message = requiredFieldMessage)
    @Size(min = 8, message = "The password should be at least 8 characters")
    private String password;

    @Valid
    @NotBlank(message = requiredFieldMessage)
    private String firstName;

    @Valid
    @NotBlank(message = requiredFieldMessage)
    private String lastName;
}
