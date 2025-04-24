package com.example.repository;

import com.example.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @NonNull
    List<Product> findAll();

    List<Product> findByIdIn(List<Integer> productIds);

    @NonNull
    Optional<Product> findById(@NonNull Integer id);

    @NonNull
    Optional<Product> findByName(@NonNull String name);

    @NonNull
    Product save(@NonNull Product product);

    void deleteById(@NonNull Integer id);
}
