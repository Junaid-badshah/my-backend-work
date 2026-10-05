package com.example.demo;

import com.example.demo.service.ReactiveAdvancedService;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class ReactiveAdvancedServiceTest {

    private final ReactiveAdvancedService advancedService = new ReactiveAdvancedService();

    @Test
    void testErrorResumeFallback() {
        StepVerifier.create(advancedService.getDataWithFallback(true))
                .expectNext("Fallback Default Data")
                .verifyComplete();
    }

    @Test
    void testProcessWithSchedulers() {
        StepVerifier.create(advancedService.processWithSchedulers("junaid"))
                .expectNext("JUNAID")
                .verifyComplete();
    }
}