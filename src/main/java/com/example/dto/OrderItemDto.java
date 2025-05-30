package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class OrderItemDto {
    private Integer productId;
    private Integer quantity;
}
