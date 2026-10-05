package com.example.demo.controller;

import com.example.demo.client.ExternalBookClient;
import com.example.demo.model.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/external")
public class ExternalBookController {

    private final ExternalBookClient externalBookClient;

    public ExternalBookController(ExternalBookClient externalBookClient) {
        this.externalBookClient = externalBookClient;
    }

    @GetMapping("/books/{id}")
    public Mono<Book> getExternalBook(@PathVariable Long id) {
        return externalBookClient.fetchExternalBook(id);
    }
}