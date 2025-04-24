package com.example.repository;

import com.example.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    Inventory findByProductId(Integer productId);

    List<Inventory> findByProductIdIn(List<Integer> productIds);

    @NonNull
    Inventory save(@NonNull Inventory inventory);
}
