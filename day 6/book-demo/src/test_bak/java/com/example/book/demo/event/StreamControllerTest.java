package com.example.demo;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class StreamControllerTest {

    @Test
    void testFluxStream() {
        Flux<String> flux = Flux.just("A", "B", "C");
        StepVerifier.create(flux)
                .expectNext("A", "B", "C")
                .verifyComplete();
    }
}