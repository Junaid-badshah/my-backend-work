package com.example.orderservice.controller;

import com.example.orderservice.client.InventoryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final InventoryClient inventoryClient;

    public OrderController(InventoryClient inventoryClient) {
        this.inventoryClient = inventoryClient;
    }

    @GetMapping("/status")
    public String getStatus() {
        return "Order Service is operational.";
    }

    @GetMapping("/check-inventory")
    public String checkInventory() {
        String inventoryResponse = inventoryClient.getInventoryStatus();
        return "Order Service -> Feign Call -> " + inventoryResponse;
    }
}