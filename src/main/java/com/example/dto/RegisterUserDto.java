package com.example.dto;

import com.example.util.ValidationMessages;
import com.example.validation.OnCreate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterUserDto {
    @Valid
    @NotBlank(message = ValidationMessages.FIELD_REQUIRED, groups = OnCreate.class)
    @Email(message = "Should be a valid email address")
    private String email;

    @Valid
    @NotBlank(message = ValidationMessages.FIELD_REQUIRED, groups = OnCreate.class)
    @Size(min = 8, message = "The password should be at least 8 characters")
    private String password;

    @Valid
    @NotBlank(message = ValidationMessages.FIELD_REQUIRED, groups = OnCreate.class)
    private String firstName;

    @Valid
    @NotBlank(message = ValidationMessages.FIELD_REQUIRED, groups = OnCreate.class)
    private String lastName;
}
