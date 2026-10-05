package com.example.demo.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class ReactiveAdvancedService {

    public Mono<String> getDataWithFallback(boolean throwError) {
        return Mono.just("Primary Data")
                .map(data -> {
                    if (throwError) {
                        throw new RuntimeException("Service failure occurred!");
                    }
                    return data;
                })
                .onErrorResume(ex -> Mono.just("Fallback Default Data"));
    }

    public Mono<String> processWithSchedulers(String input) {
        return Mono.just(input)
                .map(String::toUpperCase)
                .subscribeOn(Schedulers.boundedElastic()) // Handles blocking/I/O tasks
                .publishOn(Schedulers.parallel());       // Shifts execution to parallel threads
    }
}