package com.example.dto;

import com.example.enums.RoleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserResponseDto {
    private String email;
    private String firstName;
    private String lastName;
    @JsonIgnore()
    private String position;
    @JsonIgnore()
    private RoleType role;
}
