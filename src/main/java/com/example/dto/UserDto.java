package com.example.dto;

import com.example.util.ValidationMessages;
import com.example.validation.OnCreate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserDto extends RegisterUserDto {

    private String position = "employee";

    private String role = "user";

    @NotNull(message = ValidationMessages.FIELD_REQUIRED, groups = OnCreate.class)
    private Integer salary;

}
