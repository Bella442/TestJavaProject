package com.example.service;

import com.example.entity.Inventory;
import com.example.entity.OrderItem;
import com.example.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
        if (inventory != null) {
            inventory.setQuantity(inventory.getQuantity() + quantity);
        } else {
            inventory = new Inventory();
            inventory.setProduct(productService.getProductById(productId));
            inventory.setQuantity(quantity);
        }
        inventoryRepository.save(inventory);
    }

    public void updateInventory(List<OrderItem> newItems, List<OrderItem> oldItems) {
        Map<Integer, Integer> oldQuantities = oldItems != null
                ? oldItems.stream().collect(Collectors.toMap(item ->
                        item.getProduct().getId(),
                OrderItem::getQuantity,
                Integer::sum
        )) : new HashMap<>();

        Map<Integer, Integer> newQuantities =
                newItems.stream().collect(Collectors.toMap(item ->
                                item.getProduct().getId(),
                        OrderItem::getQuantity,
                        Integer::sum
                ));

        Set<Integer> allProductIds = new HashSet<>();
        allProductIds.addAll(oldQuantities.keySet());
        allProductIds.addAll(newQuantities.keySet());

        List<Inventory> inventoryList = inventoryRepository.findByProductIdIn(
                new ArrayList<>(allProductIds));
        inventoryList.forEach(inv -> {
            Integer oldQty = oldQuantities.getOrDefault(inv.getProduct().getId(), 0);
            Integer newQty = newQuantities.getOrDefault(inv.getProduct().getId(), 0);
           if (newQty > oldQty) {
            int diff = newQty - oldQty;
            inv.setQuantity(inv.getQuantity() - diff);
           } else {
               int diff = oldQty - newQty;
               inv.setQuantity(inv.getQuantity() + diff);
           }
        });
        inventoryRepository.saveAll(inventoryList);
    }
}
