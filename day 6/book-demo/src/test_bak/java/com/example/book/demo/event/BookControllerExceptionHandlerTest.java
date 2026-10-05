package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.beans.factory.annotation.Autowired;

@WebFluxTest
public class BookControllerExceptionHandlerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testNotFoundHandling() {
        webTestClient.get().uri("/api/books/999")
                .exchange()
                .expectStatus().isNotFound();
    }
}