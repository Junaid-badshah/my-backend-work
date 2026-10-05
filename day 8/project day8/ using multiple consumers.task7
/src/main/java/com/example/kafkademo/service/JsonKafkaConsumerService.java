package com.example.kafkademo.service;

import com.example.kafkademo.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaConsumerService {

    private static final Logger log = LoggerFactory.getLogger(JsonKafkaConsumerService.class);

    @KafkaListener(
            topics = "json-order-topic",
            groupId = "order-json-group",
            concurrency = "3" // Spawns 3 concurrent consumer threads
    )
    public void consumeOrder(
            OrderEvent orderEvent,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition
    ) {
        log.info("[Thread: {}] [Partition: {}] Received Order: {} | Item: {}",
                Thread.currentThread().getName(),
                partition,
                orderEvent.getOrderId(),
                orderEvent.getItem());
    }
}