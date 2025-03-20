package com.example.dto;

import lombok.Data;

@Data
public class UserResponseDto {
    private String email;
    private String firstName;
    private String lastName;
    private String position;
    private String role;
}
