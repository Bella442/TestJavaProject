package com.example.service;

import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.util.UtilFunctions;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        // Implementation for fetching all users
        return userRepository.findAll();
    }
    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User editUser(Long id, User user) throws EntityNotFoundException{
        return userRepository.findById(id).map(existingUser -> {
            BeanUtils.copyProperties(user, existingUser, UtilFunctions.getNullPropertyNames(user));
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));
    }
}
