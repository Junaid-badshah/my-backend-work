package com.example.book.demo.service;

import com.example.book.demo.BookCreatedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class BookEventService {

    private final ApplicationEventPublisher eventPublisher;

    public BookEventService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void createBook(String title, String isbn) {
        // Core database saving simulation
        System.out.println("BookEventService: Successfully persisted book '" + title + "' to database.");

        // Publish custom event using ApplicationEventPublisher
        BookCreatedEvent event = new BookCreatedEvent(title, isbn);
        eventPublisher.publishEvent(event);
    }
}