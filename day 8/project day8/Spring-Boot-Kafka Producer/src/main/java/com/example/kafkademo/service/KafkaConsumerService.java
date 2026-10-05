package com.example.kafkademo.service;

import com.example.kafkademo.dto.OrderEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "orders-topic", groupId = "order-processing-group")
    public void consume(OrderEvent event) {
        System.out.println("Consumed Order -> ID: " + event.getOrderId()
                + ", Item: " + event.getItem()
                + ", Price: " + event.getPrice());
    }
}