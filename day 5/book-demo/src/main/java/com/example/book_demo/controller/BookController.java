package com.example.book_demo.controller;

import com.example.book_demo.service.BookService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public String getBook(@PathVariable Long id) {
        long startTime = System.currentTimeMillis();
        String result = bookService.getBookById(id);
        long timeTaken = System.currentTimeMillis() - startTime;
        return result + " (Time: " + timeTaken + " ms)\n";
    }

    @DeleteMapping("/{id}")
    public String evictBook(@PathVariable Long id) {
        bookService.clearCacheForBook(id);
        return "Cache cleared for Book ID: " + id + "\n";
    }
}
