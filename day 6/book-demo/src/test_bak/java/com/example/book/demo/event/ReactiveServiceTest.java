package com.example.demo;

import com.example.demo.service.ReactiveService;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import java.util.List;

class ReactiveServiceTest {

    private final ReactiveService reactiveService = new ReactiveService();

    @Test
    void testMonoExample() {
        StepVerifier.create(reactiveService.runMonoExample())
                .expectNext("Welcome back, JUNAID")
                .verifyComplete();
    }

    @Test
    void testFluxExample() {
        StepVerifier.create(reactiveService.runFluxExample())
                .expectNext(List.of("SPRING", "KUBERNETES", "REACTOR"))
                .verifyComplete();
    }

    @Test
    void testZipExample() {
        StepVerifier.create(reactiveService.runZipExample())
                .expectNext("Junaid works as a Software Engineer")
                .verifyComplete();
    }
}