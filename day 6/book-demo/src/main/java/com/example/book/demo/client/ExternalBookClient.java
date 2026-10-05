package com.example.demo.client;

import com.example.demo.model.Book;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ExternalBookClient {

    private final WebClient webClient;

    public ExternalBookClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://jsonplaceholder.typicode.com").build();
    }

    public Mono<Book> fetchExternalBook(Long id) {
        return this.webClient.get()
                .uri("/todos/{id}", id)
                .retrieve()
                .bodyToMono(Book.class)
                .onErrorResume(ex -> Mono.just(new Book(id, "Fallback Book Title", "Default Author")));
    }
}