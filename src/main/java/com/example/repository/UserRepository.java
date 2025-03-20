package com.example.repository;

import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @NonNull
    User save(@NonNull User user);

    @NonNull
    Optional<User> findById(@NonNull UUID id);

    Optional<User> findByEmail(String email);

    @NonNull
    List<User> findAll();

}
