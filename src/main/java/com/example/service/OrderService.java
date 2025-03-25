package com.example.service;

import com.example.dto.OrderDto;
import com.example.dto.OrderResponseDto;
import com.example.entity.Order;
import com.example.entity.Product;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.repository.OrderRepository;
import com.example.repository.ProductRepository;
import com.example.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<OrderResponseDto> getAllOrders() {
        return mapOrdersListToOrderResponseDtoList(orderRepository.findAll());
    }

    public List<OrderResponseDto> findOrdersByUserId(UUID userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return mapOrdersListToOrderResponseDtoList(orders);
    }

    @Transactional
    public OrderResponseDto createOrder(OrderDto orderDto) {
        User user = userRepository.findById(orderDto.getUserId()).orElseThrow(() ->
                new EntityNotFoundException("User not found with id: " + orderDto.getUserId()));
        List<Product> products = getProducts(orderDto.getProductIds());
        double totalPrice = calculateTotalPrice(products);

        Order orderEntity = new Order();
        orderEntity.setUser(user);
        orderEntity.setProducts(products);
        orderEntity.setTotalPrice(totalPrice);
        return mapOrderToOrderResponseDto(orderRepository.save(orderEntity));
    }

    public OrderResponseDto editOrderProducts(int orderId, List<Integer> productIds) {
        List<Product> products = getProducts(productIds);

        Order order = orderRepository.findById(orderId).map(existing -> {
            existing.setProducts(products);
            existing.setTotalPrice(calculateTotalPrice(products));
            return orderRepository.save(existing);
        }).orElseThrow(() -> new EntityNotFoundException("Order non found with id" + orderId));
        return mapOrderToOrderResponseDto(order);
    }

    public void deleteOrder(int orderId) {
        Order order = getOrderById(orderId);
        orderRepository.delete(order);
    }

    public Order getOrderById(int orderId) {
        return orderRepository.findById(orderId).orElseThrow(() ->
                new EntityNotFoundException("Order not found with id: " + orderId));
    }

    public double calculateTotalPrice(List<Product> products) {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

    public OrderResponseDto updateOrderStatus(int orderId, String newStatus) {
        Order order = orderRepository.findById(orderId).map(existing -> {
            if (existing.getStatus().equals("COMPLETED")) {
                return null;
            }
            existing.setStatus(newStatus);
            return orderRepository.save(existing);
        }).orElseThrow(() -> new EntityNotFoundException("Order non found or already completed with id " + orderId));
        return mapOrderToOrderResponseDto(order);
    }

    public List<Product> getProducts(List<Integer> productIds) {
        return productRepository.findByIdIn(productIds);
    }

    public OrderResponseDto mapOrderToOrderResponseDto(Order order) {
        return new OrderResponseDto(order.getId(), userMapper.mapUserToUserResponseDto(order.getUser()),
                order.getProducts(), order.getTotalPrice(), order.getOrderDate(), order.getStatus());
    }

    public List<OrderResponseDto> mapOrdersListToOrderResponseDtoList(List<Order> orders) {
        return orders.stream().map(this::mapOrderToOrderResponseDto).collect(Collectors.toList());
    }
}
