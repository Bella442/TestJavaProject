package com.example.repository;

import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @NonNull
    User save(@NonNull User user);

    @NonNull
    Optional<User> findById(@NonNull Long id);  // Assuming Long as the primary key type

    @NonNull
    List<User> findAll();

}
