package com.example.kafkademo;

import com.example.kafkademo.dto.OrderEvent;
import com.example.kafkademo.service.KafkaProducerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
class KafkaIntegrationTest {

    @Autowired
    private KafkaProducerService producerService;

    @Test
    void testProducerAndConsumerPipeline() {
        OrderEvent testOrder = new OrderEvent("TEST-100", "Headphones", 79.99);
        producerService.sendOrder(testOrder);

        producerService.sendStringMessage("Test string alert payload");

        await().atMost(5, TimeUnit.SECONDS).untilAsserted(() -> {
            // Assertions or verification
        });
    }
}