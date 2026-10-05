package com.example.book.demo.controller;

import com.example.book.demo.service.BookCacheService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cache/books")
public class BookCacheController {

    private final BookCacheService bookCacheService;

    public BookCacheController(BookCacheService bookCacheService) {
        this.bookCacheService = bookCacheService;
    }

    // GET /api/cache/books/1
    @GetMapping("/{id}")
    public String getBook(@PathVariable Long id) {
        long startTime = System.currentTimeMillis();
        String result = bookCacheService.getBookTitleById(id);
        long duration = System.currentTimeMillis() - startTime;
        return result + " (Fetched in " + duration + " ms)";
    }

    // DELETE /api/cache/books/1
    @DeleteMapping("/{id}")
    public String evictCache(@PathVariable Long id) {
        bookCacheService.deleteBook(id);
        return "Cache evicted for book ID: " + id;
    }
}