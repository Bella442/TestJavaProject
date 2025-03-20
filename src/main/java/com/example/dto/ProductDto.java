package com.example.dto;

import com.example.util.ValidationMessages;
import com.example.validation.OnCreate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class ProductDto {

    @NotBlank(message = ValidationMessages.FIELD_REQUIRED, groups = OnCreate.class)
    private String name;

    private String description;

    @NotNull(message = ValidationMessages.FIELD_REQUIRED)
    @PositiveOrZero
    private double price;
}
