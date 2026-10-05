package com.example.book.demo.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class BookCacheService {

    // Cache hit on second call with the same ID
    @Cacheable(value = "books", key = "#id")
    public String getBookTitleById(Long id) {
        simulateSlowDatabaseCall();
        return "Book Title for ID: " + id;
    }

    // Evicts/clears entry from cache when deleted
    @CacheEvict(value = "books", key = "#id")
    public void deleteBook(Long id) {
        // Delete logic here
    }

    private void simulateSlowDatabaseCall() {
        try {
            Thread.sleep(3000); // 3-second delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}