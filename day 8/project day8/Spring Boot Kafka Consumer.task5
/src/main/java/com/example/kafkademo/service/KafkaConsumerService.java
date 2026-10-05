package com.example.kafkademo.service;

import com.example.kafkademo.dto.OrderEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "order-topic", groupId = "kafka-demo-group")
    public void consume(OrderEvent orderEvent) {
        System.out.println("Received Order: " + orderEvent.getOrderId() + " | Item: " + orderEvent.getItem());
    }
}