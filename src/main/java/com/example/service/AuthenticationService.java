package com.example.service;

import com.example.dto.LoginUserDto;
import com.example.dto.RegisterUserDto;
import com.example.dto.UserResponseDto;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService extends BaseUserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, UserMapper userMapper) {
        super(userRepository);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userMapper = userMapper;
    }

    public UserResponseDto signup(RegisterUserDto input) {
        if (userExists(input.getEmail())) {
            throw new IllegalArgumentException("User with this email already exists.");
        }
        User user = new User(input.getEmail(), passwordEncoder.encode(input.getPassword()),
                input.getFirstName(), input.getLastName());

        return userMapper.mapUserToUserResponseDto(userRepository.save(user));
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}
