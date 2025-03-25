package com.example.dto;

import com.example.entity.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.List;
@AllArgsConstructor
public class OrderResponseDto {
    @JsonProperty(value = "orderId")
    private int orderId;

    @JsonProperty(value = "customer")
    private UserResponseDto customer;

    @JsonProperty(value = "products")
    private List<Product> products;

    @JsonProperty(value = "totalPrice")
    private double totalPrice;

    @JsonProperty(value = "orderDate")
    private Date orderDate;

    @JsonProperty(value = "status")
    private String status;
}
