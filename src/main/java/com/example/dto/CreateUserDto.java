package com.example.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateUserDto extends RegisterUserDto {

    private String position = "employee";

    private String role = "user";

    @Valid
    @NotBlank(message = "This field is required")
    private Integer salary;

}
