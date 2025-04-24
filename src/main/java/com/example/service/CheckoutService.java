package com.example.service;

import com.example.entity.Order;
import com.example.enums.StatusEnum;
import com.example.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckoutService {
    private final OrderService orderService;
    private final OrderRepository orderRepository;

    public void checkoutOrder(int orderId, double amount) throws IllegalArgumentException {
        Order order = orderService.getOrderById((orderId));
        if (amount < order.getTotalPrice()) {
            throw new IllegalArgumentException("Amount is less than the total price of the order.");
        } else {
            order.setPaid(true);
            order.setStatus(StatusEnum.COMPLETED);
            orderRepository.save(order);
        }
    }
}
