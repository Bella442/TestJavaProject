package com.example.service;

import com.example.dto.UserDto;
import com.example.dto.UserResponseDto;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.repository.UserRepository;
import com.example.util.UtilFunctions;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service

public class UserService extends BaseUserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        super(userRepository);
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserResponseDto getUserByEmail(String email) throws EntityNotFoundException {
        return userMapper.mapUserToUserResponseDto(userRepository.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException("User not found with email: " + email)
        ));
    }

    public UserResponseDto createUser(UserDto userDto) throws IllegalArgumentException {
        if (userExists(userDto.getEmail())) {
            throw new IllegalArgumentException("User with this email already exists.");
        }
        User savedUser = userRepository.save(userMapper.mapUserDtoToUserEntity(userDto));
        return userMapper.mapUserToUserResponseDto(savedUser);
    }

    public UserResponseDto editUser(UUID id, UserDto user) throws EntityNotFoundException {
        return userRepository.findById(id).map(existingUser -> {
            BeanUtils.copyProperties(user, existingUser, UtilFunctions.getNullPropertyNames(user));
            return userMapper.mapUserToUserResponseDto(userRepository.save(existingUser));
        }).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    public void deleteUser(UUID id) throws EntityNotFoundException {
        userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        userRepository.deleteById(id);
    }
}
