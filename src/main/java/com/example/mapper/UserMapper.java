package com.example.mapper;

import com.example.dto.CreateUserDto;
import com.example.dto.RegisterUserResponseDto;
import com.example.entity.User;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class UserMapper {
    public User mapUserDtoToUserEntity(CreateUserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPosition(userDto.getPosition());
        user.setRole(userDto.getRole());
        user.setSalary(userDto.getSalary());
        return user;
    }

    public RegisterUserResponseDto mapUserToRegisterUserResponseDto(User user) {
        RegisterUserResponseDto userDto = new RegisterUserResponseDto();

        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());

        return userDto;
    }
}
