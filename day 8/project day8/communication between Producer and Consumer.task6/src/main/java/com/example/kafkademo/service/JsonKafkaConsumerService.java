package com.example.kafkademo.service;

import com.example.kafkademo.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaConsumerService {

    private static final Logger log = LoggerFactory.getLogger(JsonKafkaConsumerService.class);

    @KafkaListener(topics = "json-order-topic", groupId = "order-json-group")
    public void consumeOrder(OrderEvent orderEvent) {
        log.info("[JSON Deserialized] Received Order ID: {}", orderEvent.getOrderId());
        log.info("[JSON Deserialized] Item: {} | Price: ${}", orderEvent.getItem(), orderEvent.getPrice());
    }
}