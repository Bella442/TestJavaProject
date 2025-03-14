package com.example.service;

import org.springframework.stereotype.Service;

@Service

public class UserService {

    public String getAllUsers() {
        // Implementation for fetching all users
        return "List of all users";
    }
    public String createUser(String name, String email) {
        // Implement user creation logic here
        System.out.println("Creating user: " + name + ", email: " + email);
        return "Creating user: " + name + ", email: " + email + "/n User added successfully";
    }
}
