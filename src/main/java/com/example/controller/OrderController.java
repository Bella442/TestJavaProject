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
    public OrderResponseDto editOrderProducts(@PathVariable Integer id, @Validated({OnUpdate.class, Default.class}) @RequestBody OrderDto orderDto) {
        return orderService.editOrderProducts(id, orderDto);
    }

    @PatchMapping("/status/{id}")
    public OrderResponseDto updateOrderStatus(@PathVariable Integer id, @RequestBody UpdateStatusDto statusDto) {
        return orderService.updateOrderStatus(id, statusDto.status());
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
    }
}


