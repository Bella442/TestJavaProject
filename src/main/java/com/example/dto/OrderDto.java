package com.example.dto;

import com.example.util.ValidationMessages;
import com.example.validation.OnCreate;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class OrderDto {
    @NotNull(message = ValidationMessages.FIELD_REQUIRED, groups = OnCreate.class)
    private UUID userId;

    @NotEmpty(message = ValidationMessages.FIELD_REQUIRED)
    private List<Integer> productIds;

}
