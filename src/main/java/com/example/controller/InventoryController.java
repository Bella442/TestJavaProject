package com.example.controller;

import com.example.dto.InventoryDto;
import com.example.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<HttpStatus> addInventory(@RequestBody InventoryDto inventoryDto) {
        inventoryService.addInventory(inventoryDto.getProductId(), inventoryDto.getQuantity());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
