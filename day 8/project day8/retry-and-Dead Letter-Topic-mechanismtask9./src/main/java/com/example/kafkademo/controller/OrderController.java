package com.example.kafkademo.controller;

import com.example.kafkademo.dto.OrderEvent;
import com.example.kafkademo.service.JsonKafkaProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final JsonKafkaProducerService producerService;

    public OrderController(JsonKafkaProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderEvent orderEvent) {
        producerService.sendOrder(orderEvent);
        return ResponseEntity.ok("Native JSON order published to Kafka");
    }
}