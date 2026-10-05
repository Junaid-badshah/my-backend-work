package com.example.demo.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;

@Service
public class ReactiveService {

    public Mono<String> runMonoExample() {
        return Mono.just("junaid")
                .map(String::toUpperCase)
                .flatMap(name -> Mono.just("Welcome back, " + name));
    }

    public Mono<List<String>> runFluxExample() {
        return Flux.just("Java", "Spring", "Docker", "Kubernetes", "Reactor")
                .filter(tech -> tech.length() > 5)
                .map(String::toUpperCase)
                .collectList();
    }

    public Mono<String> runZipExample() {
        Mono<String> userMono = Mono.just("Junaid");
        Mono<String> roleMono = Mono.just("Software Engineer");

        return Mono.zip(userMono, roleMono, (user, role) -> user + " works as a " + role);
    }
}