package com.example.mapper;

import com.example.dto.UserDto;
import com.example.dto.UserResponseDto;
import com.example.entity.User;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class UserMapper {
    public User mapUserDtoToUserEntity(UserDto userDto) {
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

    public UserResponseDto mapUserToRegisterUserResponseDto(User user) {
        UserResponseDto userDto = new UserResponseDto();

        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());

        return userDto;
    }
}
