package com.example.dto;

import com.example.util.ValidationMessages;
import jakarta.validation.constraints.NotBlank;

public record UpdateStatusDto(@NotBlank(message = ValidationMessages.FIELD_REQUIRED) String status) {
}
