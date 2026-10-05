package com.example.kafkademo.service;

import com.example.kafkademo.dto.OrderEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrder(OrderEvent event) {
        // Sends message asynchronously with orderId as key for partition distribution
        kafkaTemplate.send("orders-topic", event.getOrderId(), event);
    }
}