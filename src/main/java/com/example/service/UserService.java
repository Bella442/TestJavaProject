package com.example.service;

import com.example.dto.UserDto;
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

    public UserService(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByEmail(String email) throws EntityNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException("User not found with email: " + email)
        );
    }

    public User createUser(UserDto userDto) throws IllegalArgumentException {
        UserMapper userMapper = new UserMapper();
        if (userExists(userDto.getEmail())) {
            throw new IllegalArgumentException("User with this email already exists.");
        }
        return userRepository.save(userMapper.mapUserDtoToUserEntity(userDto));
    }

    public User editUser(UUID id, UserDto user) throws EntityNotFoundException {
        return userRepository.findById(id).map(existingUser -> {
            BeanUtils.copyProperties(user, existingUser, UtilFunctions.getNullPropertyNames(user));
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    public void deleteUser(UUID id) throws EntityNotFoundException {
        userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        userRepository.deleteById(id);
    }
}
