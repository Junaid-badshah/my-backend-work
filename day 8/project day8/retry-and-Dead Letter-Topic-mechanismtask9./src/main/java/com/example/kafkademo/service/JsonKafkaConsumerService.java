package com.example.kafkademo.service;

import com.example.kafkademo.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.DltStrategy;
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaConsumerService {

    private static final Logger log = LoggerFactory.getLogger(JsonKafkaConsumerService.class);

    @RetryableTopic(
            attempts = "3",
            backoff = @Backoff(delay = 1000, multiplier = 2.0),
            topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE,
            dltStrategy = DltStrategy.FAIL_ON_ERROR
    )
    @KafkaListener(topics = "json-order-topic", groupId = "order-json-group", concurrency = "3")
    public void consumeOrder(
            OrderEvent orderEvent,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition
    ) {
        log.info("[Thread: {}] [Partition: {}] Processing Order: {}",
                Thread.currentThread().getName(), partition, orderEvent.getOrderId());

        if (orderEvent.getOrderId() != null && orderEvent.getOrderId().startsWith("ORD-FAIL")) {
            log.error("Processing failed for Order: {}", orderEvent.getOrderId());
            throw new RuntimeException("Simulated processing error for " + orderEvent.getOrderId());
        }

        log.info("Successfully processed Order: {}", orderEvent.getOrderId());
    }

    @DltHandler
    public void handleDltOrder(
            OrderEvent orderEvent,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.OFFSET) long offset
    ) {
        log.error("[DLT RECOVERY] Permanently failed Order: {} | Source Topic: {} | Offset: {}",
                orderEvent.getOrderId(), topic, offset);
    }
}