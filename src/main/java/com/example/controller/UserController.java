package com.example.controller;

import com.example.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    private String getAllUsers() {
        String users = userService.getAllUsers();
        System.out.println(users);
        return users;
    }
    @PostMapping("/users")
    public String createUser(@RequestParam String name, String email) {
        return userService.createUser(name, email);
    }
}
