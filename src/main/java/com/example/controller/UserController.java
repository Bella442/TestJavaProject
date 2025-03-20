package com.example.controller;

import com.example.dto.UserDto;
import com.example.entity.User;
import com.example.service.UserService;
import com.example.validation.OnCreate;
import com.example.validation.OnUpdate;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    private List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{email}")
    public User getUserByEmail(@PathVariable String email) throws EntityNotFoundException {
        return userService.getUserByEmail(email);
    }

    @PostMapping
    public User createUser(@Validated({OnCreate.class, Default.class}) @RequestBody UserDto user) throws IllegalArgumentException {
        return userService.createUser(user);
    }

    @PatchMapping("/{id}")
    public User editUser(@PathVariable UUID id, @Validated({OnUpdate.class, Default.class}) @RequestBody UserDto user) throws EntityNotFoundException {
        return userService.editUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) throws EntityNotFoundException {
        userService.deleteUser(id);
    }
}
