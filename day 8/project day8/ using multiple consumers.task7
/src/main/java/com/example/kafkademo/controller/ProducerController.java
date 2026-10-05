package com.example.kafkademo.controller;

import com.example.kafkademo.dto.OrderEvent;
import com.example.kafkademo.service.KafkaProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/producer")
public class ProducerController {

    private final KafkaProducerService producerService;

    public ProducerController(KafkaProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/string")
    public ResponseEntity<String> sendString(@RequestParam String message) {
        producerService.sendStringMessage(message);
        return ResponseEntity.ok("String message sent to Kafka");
    }

    @PostMapping("/order")
    public ResponseEntity<String> sendOrder(@RequestBody OrderEvent orderEvent) {
        producerService.sendOrder(orderEvent);
        return ResponseEntity.ok("Order JSON sent to Kafka");
    }
}