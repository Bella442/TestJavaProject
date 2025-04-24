package com.example.controller;

import com.example.service.CheckoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checkout")
@RequiredArgsConstructor
public class CheckoutController {
    private final CheckoutService checkoutService;

    @PostMapping
    public ResponseEntity<HttpStatus> checkoutOrder(@RequestParam int orderId, @RequestParam double amount) throws IllegalArgumentException {
        checkoutService.checkoutOrder(orderId, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
