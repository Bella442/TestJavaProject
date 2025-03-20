package com.example.controller;

import com.example.dto.ProductDto;
import com.example.entity.Product;
import com.example.service.ProductService;
import com.example.validation.OnCreate;
import com.example.validation.OnUpdate;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) throws EntityNotFoundException {
        return productService.getProductById(id);
    }

    @PostMapping
    public Product addProduct(@Validated({OnCreate.class, Default.class}) @RequestBody ProductDto product) throws IllegalArgumentException {
        return productService.createProduct(product);
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @Validated({OnUpdate.class, Default.class})
    @RequestBody ProductDto updatedProduct) throws EntityNotFoundException {
        return productService.updateProduct(id, updatedProduct);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) throws EntityNotFoundException {
        productService.deleteProductById(id);
    }
}
