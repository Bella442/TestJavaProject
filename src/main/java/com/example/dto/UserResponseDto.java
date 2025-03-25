package com.example.dto;

import com.example.enums.RoleType;
import lombok.Data;

@Data
public class UserResponseDto {
    private String email;
    private String firstName;
    private String lastName;
    private String position;
    private RoleType role;
}
