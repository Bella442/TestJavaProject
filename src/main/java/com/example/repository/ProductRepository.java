package com.example.repository;

import com.example.dto.ProductDto;
import com.example.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @NonNull
    List<Product> findAll();

    @NonNull
    Optional<Product> findById(@NonNull Long id);

    @NonNull
    Optional<Product> findByName(@NonNull String name);

    @NonNull
    Product save(@NonNull ProductDto product);

    void deleteById(@NonNull Long id);
}
