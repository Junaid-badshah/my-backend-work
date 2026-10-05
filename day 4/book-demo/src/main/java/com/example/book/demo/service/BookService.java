package com.example.book.demo.service;

import com.example.book.demo.model.Book;
import com.example.book.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Cacheable(value = "books", key = "#id")
    public Book getBookById(Long id) {
        System.out.println("--- DB QUERY EXECUTED: Fetching from database ---");
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @CachePut(value = "books", key = "#book.id")
    public Book updateBook(Book book) {
        System.out.println("--- CACHE UPDATE: Refreshing cache entry ---");
        return bookRepository.save(book);
    }

    @CacheEvict(value = "books", key = "#id")
    public void deleteBook(Long id) {
        System.out.println("--- CACHE EVICTION: Removing entry from cache ---");
        bookRepository.deleteById(id);
    }
}