package com.example.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private final String name;

    @Column(name = "description")
    private final String description;

    @Column(name = "price")
    private final double price;

//    public Product(String name, String description, double price) {
//        this.name = name;
//        this.description = description;
//        this.price = price;
//    }
}
