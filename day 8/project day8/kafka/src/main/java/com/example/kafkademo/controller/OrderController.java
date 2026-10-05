package com.example.kafkademo.controller;

import com.example.kafkademo.producer.KafkaProducerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final KafkaProducerService producerService;

    public OrderController(KafkaProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping
    public String publishOrder(@RequestParam String userId, @RequestParam String event) {
        producerService.sendOrderEvent(userId, event);
        return "Event published successfully!";
    }
}