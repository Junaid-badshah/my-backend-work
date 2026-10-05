package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/reactive")
public class ReactiveController {

    @GetMapping("/welcome/{name}")
    public Mono<String> getWelcome(@PathVariable String name) {
        return Mono.just(name)
                .map(String::toUpperCase)
                .map(n -> "Welcome, " + n + "!");
    }

    @GetMapping("/tech")
    public Flux<String> getTechnologies() {
        return Flux.just("Java", "Spring WebFlux", "Project Reactor", "Docker", "Kubernetes")
                .filter(tech -> tech.length() > 5)
                .map(String::toUpperCase);
    }
}