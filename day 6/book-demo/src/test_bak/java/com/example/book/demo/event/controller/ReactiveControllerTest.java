package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(ReactiveController.class)
class ReactiveControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testGetWelcome() {
        webTestClient.get().uri("/api/reactive/welcome/junaid")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("Welcome, JUNAID!");
    }

    @Test
    void testGetTechnologies() {
        webTestClient.get().uri("/api/reactive/tech")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(String.class)
                .hasSize(3)
                .contains("SPRING WEBFLUX", "PROJECT REACTOR", "KUBERNETES");
    }
}