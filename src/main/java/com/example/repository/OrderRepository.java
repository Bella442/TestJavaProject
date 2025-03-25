package com.example.repository;

import com.example.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @NonNull
    List<Order> findAll();

    @NonNull
    List<Order> findByUserId(UUID userId);

    @NonNull
    Order save(@NonNull Order order);

    @NonNull
    void deleteById(@NonNull Integer orderId);
}

