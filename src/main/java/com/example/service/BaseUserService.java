package com.example.service;

import com.example.repository.UserRepository;

public class BaseUserService {

    private final UserRepository userRepository;

    public BaseUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean userExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
