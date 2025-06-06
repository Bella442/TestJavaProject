package com.example.service;

import com.example.dto.OrderDto;
import com.example.dto.OrderItemDto;
import com.example.dto.OrderResponseDto;
import com.example.entity.*;
import com.example.enums.StatusEnum;
import com.example.mapper.UserMapper;
import com.example.repository.OrderRepository;
import com.example.repository.ProductRepository;
import com.example.repository.UserRepository;
import com.example.util.UtilFunctions;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final InventoryService inventoryService;

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
        List<Product> products = getProducts(orderDto.getOrderItems().stream().map(OrderItemDto::getProductId).collect(Collectors.toList()));
        checkAllProductsAvailable(orderDto.getOrderItems());

        Order orderEntity = new Order();

        List<OrderItem> orderItems = mapOrderItemDtosListToOrderItemList(orderDto.getOrderItems(), orderEntity, products);
        double totalPrice = calculateTotalPrice(orderItems);

        orderEntity.setUser(user);
        orderEntity.setOrderItems(orderItems);
        orderEntity.setTotalPrice(totalPrice);
        inventoryService.updateInventory(orderItems, null);
        return mapOrderToOrderResponseDto(orderRepository.save(orderEntity));
    }

    public OrderResponseDto editOrderProducts(Integer orderId, OrderDto orderDto) {
        List<Product> products = getProducts(orderDto.getOrderItems().stream().map(OrderItemDto::getProductId).collect(Collectors.toList()));
        checkAllProductsAvailable(orderDto.getOrderItems());
        List<OrderItem> previousOrderItems = new ArrayList<>();

        Order orderEntity = orderRepository.findById(orderId).map(existing -> {
            previousOrderItems.addAll(existing.getOrderItems());
            existing.getOrderItems().clear();
            existing.getOrderItems().addAll(mapOrderItemDtosListToOrderItemList(orderDto.getOrderItems(), existing, products));
            existing.setTotalPrice(calculateTotalPrice(existing.getOrderItems()));
            return orderRepository.save(existing);
        }).orElseThrow(() -> new EntityNotFoundException("Order non found with id" + orderId));
        inventoryService.updateInventory(orderEntity.getOrderItems(), previousOrderItems);
        return mapOrderToOrderResponseDto(orderEntity);
    }

    public void deleteOrder(Integer orderId) {
        Order order = getOrderById(orderId);
        orderRepository.delete(order);
    }

    public Order getOrderById(Integer orderId) {
        return orderRepository.findById(orderId).orElseThrow(() ->
                new EntityNotFoundException("Order not found with id: " + orderId));
    }

    public double calculateTotalPrice(List<OrderItem> orderItems) {
        return UtilFunctions.round(orderItems.stream().mapToDouble(orderItem ->
                orderItem.getProduct().getPrice() * orderItem.getQuantity()).sum(), 2);
    }

    public OrderResponseDto updateOrderStatus(Integer orderId, StatusEnum newStatus) {
        Order order = orderRepository.findById(orderId).map(existing -> {
            if (existing.getStatus().equals(StatusEnum.COMPLETED)) {
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
                mapOrderItemsToOrderItemDto(order.getOrderItems()), order.getTotalPrice(), order.getOrderDate(), order.getStatus());
    }

    public List<OrderResponseDto> mapOrdersListToOrderResponseDtoList(List<Order> orders) {
        return orders.stream().map(this::mapOrderToOrderResponseDto).collect(Collectors.toList());
    }

    public List<OrderItem> mapOrderItemDtosListToOrderItemList(List<OrderItemDto> orderItems, Order order, List<Product> products) {
        return orderItems.stream().map(orderItemDto -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(products.stream().filter(product -> Objects.equals(product.getId(), orderItemDto.getProductId())).findFirst().orElse(null));
            orderItem.setQuantity(orderItemDto.getQuantity());
            return orderItem;
        }).collect(Collectors.toList());
    }

    public List<OrderItemDto> mapOrderItemsToOrderItemDto(List<OrderItem> orderItems) {
        return orderItems.stream().map(orderItem -> new OrderItemDto(orderItem.getProduct().getId(), orderItem.getQuantity()))
                .collect(Collectors.toList());
    }

    public void checkAllProductsAvailable(List<OrderItemDto> orderItems) {
        List<Inventory> inventory = inventoryService.getAvailability(orderItems.stream().map(OrderItemDto::getProductId).collect(Collectors.toList()));
        List<OrderItemDto> unavailableProducts = orderItems.stream().filter(item -> {
            Inventory current = inventory.stream().filter(inv -> Objects.equals(inv.getProduct().getId(), item.getProductId())).findFirst().orElse(null);
            return  current == null || current.getQuantity() < item.getQuantity();
        }).toList();

        if (!unavailableProducts.isEmpty()) {
            throw new IllegalArgumentException("The following products are not available or availability is not enough: " +
                    unavailableProducts.stream().map(item -> item.getProductId().toString()).collect(Collectors.joining(", ")) +
                    ". Please remove them from the order to proceed.");
        }
    }
}
