package com.example.kafkademo.service;

import com.example.kafkademo.dto.OrderEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    // Handles simple string payloads called by ProducerController line 30
    public void sendStringMessage(String message) {
        kafkaTemplate.send("order-topic", message);
    }

    // Handles OrderEvent objects
    public void sendOrder(OrderEvent orderEvent) {
        try {
            String jsonPayload = objectMapper.writeValueAsString(orderEvent);
            kafkaTemplate.send("order-topic", jsonPayload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize OrderEvent to JSON", e);
        }
    }
}