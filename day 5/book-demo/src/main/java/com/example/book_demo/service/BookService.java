package com.example.book_demo.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "books")
public class BookService {

    @Cacheable(key = "#id")
    public String getBookById(Long id) {
        simulateSlowDatabaseCall();
        return "Book Details for ID: " + id;
    }

    @CacheEvict(key = "#id")
    public void clearCacheForBook(Long id) {
    }

    private void simulateSlowDatabaseCall() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
