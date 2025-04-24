package com.example.entity;

import com.example.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<OrderItem> orderItems;

    private double totalPrice;

    private boolean isPaid = false;

    private final Date orderDate = new Date();
    @Enumerated(EnumType.STRING)
    private StatusEnum status = StatusEnum.PENDING;

}
