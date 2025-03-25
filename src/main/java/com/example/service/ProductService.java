package com.example.service;

import com.example.dto.ProductDto;
import com.example.entity.Product;
import com.example.repository.ProductRepository;
import com.example.util.UtilFunctions;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int id) throws EntityNotFoundException {
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
    }

    public Product createProduct(ProductDto product) throws IllegalArgumentException {
        if (productExists(product.getName())) {
            throw new IllegalArgumentException("Product with the same name already exists.");
        }
        Product productEntity = new Product(product.getName(), product.getDescription(), product.getPrice());
        return productRepository.save(productEntity);
    }

    public Product updateProduct(int id, ProductDto updatedProduct) throws EntityNotFoundException {
        return productRepository.findById(id).map(existing -> {
            BeanUtils.copyProperties(updatedProduct, existing, UtilFunctions.getNullPropertyNames(updatedProduct));
            return productRepository.save(existing);
        }).orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
    }

    public void deleteProductById(int id) throws EntityNotFoundException {
        productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
        productRepository.deleteById(id);
    }

    public boolean productExists(String name) {
        return productRepository.findByName(name).isPresent();
    }
}
