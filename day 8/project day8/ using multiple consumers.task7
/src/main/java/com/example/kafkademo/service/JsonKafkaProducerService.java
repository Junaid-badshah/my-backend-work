package com.example.kafkademo.service;

import com.example.kafkademo.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaProducerService {

    private static final Logger log = LoggerFactory.getLogger(JsonKafkaProducerService.class);
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public JsonKafkaProducerService(KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrder(OrderEvent orderEvent) {
        log.info("Publishing JSON Order event: {}", orderEvent.getOrderId());
        kafkaTemplate.send("json-order-topic", orderEvent.getOrderId(), orderEvent);
    }
}