package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PatchMapping("/users/{id}")
    public User editUser(@PathVariable Long id, @RequestBody User user ) {
        return userService.editUser(id, user);
    }
}
