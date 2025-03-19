package com.example.controller;

import com.example.dto.CreateUserDto;
import com.example.entity.User;
import com.example.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    private List<User> getAllUsers() {
        List<User> users = userService.getAllUsers();
        System.out.println(users);
        return users;
    }

    @PostMapping("/users")
    public User createUser(@Valid @RequestBody CreateUserDto user) {
        return userService.createUser(user);
    }

    @PatchMapping("/users/{id}")
    public User editUser(@PathVariable UUID id, @RequestBody User user) {
        return userService.editUser(id, user);
    }
}
