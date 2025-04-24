package com.example.service;

import com.example.entity.Inventory;
import com.example.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final ProductService productService;

    public List<Inventory> getAvailability(List<Integer> productIds) {
        return inventoryRepository.findByProductIdIn(productIds);
    }

    public void addInventory(Integer productId, Integer quantity) {
        Inventory inventory = inventoryRepository.findByProductId(productId);
        if (inventory != null){
        inventory.setQuantity(inventory.getQuantity() + quantity);
        } else {
            inventory = new Inventory();
            inventory.setProduct(productService.getProductById(productId));
            inventory.setQuantity(quantity);
        }
        inventoryRepository.save(inventory);
    }

    public void updateInventory(Integer productId, Integer quantity) {
        Inventory inventory = inventoryRepository.findByProductId(productId);
        inventory.setQuantity(inventory.getQuantity() - quantity);
        inventoryRepository.save(inventory);
    }
}
