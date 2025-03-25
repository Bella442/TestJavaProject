package com.example.controller;

import com.example.dto.OrderDto;
import com.example.dto.OrderResponseDto;
import com.example.dto.UpdateStatusDto;
import com.example.service.OrderService;
import com.example.validation.OnCreate;
import com.example.validation.OnUpdate;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public List<OrderResponseDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping
    public OrderResponseDto createOrder(@Validated({OnCreate.class, Default.class}) @RequestBody OrderDto orderDto) {
        return orderService.createOrder(orderDto);
    }

    @PatchMapping("/{id}")
    public OrderResponseDto editOrderProducts(@PathVariable int id, @Validated({OnUpdate.class, Default.class}) @RequestBody OrderDto orderDto) {
        return orderService.editOrderProducts(id, orderDto.getProductIds());
    }

    @PatchMapping("/{id}/{status}")
    public OrderResponseDto updateOrderStatus(@PathVariable int id, @RequestBody UpdateStatusDto statusDto) {
        return orderService.updateOrderStatus(id, statusDto.status());
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable int id) {
        orderService.deleteOrder(id);
    }
}


