package com.example.kafkademo.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderEvent(String userId, String eventDetails) {
        // Passing userId as key guarantees events for the same user land in the same partition
        kafkaTemplate.send("order-events", userId, eventDetails);
        System.out.printf("Sent Message -> Key: %s | Payload: %s%n", userId, eventDetails);
    }
}