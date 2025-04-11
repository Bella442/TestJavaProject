package com.example.dto;

import com.example.enums.RoleType;
import com.example.util.ValidationMessages;
import com.example.validation.OnCreate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserDto extends BaseUserDto {

    private String position = "employee";

    private RoleType role;

    @NotNull(message = ValidationMessages.FIELD_REQUIRED, groups = OnCreate.class)
    private Integer salary;

}
