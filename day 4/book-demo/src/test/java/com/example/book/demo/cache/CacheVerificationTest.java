package com.example.book.demo.cache;

import com.example.book.demo.model.Book;
import com.example.book.demo.repository.BookRepository;
import com.example.book.demo.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("caffeine")
class CacheVerificationTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CacheManager cacheManager;

    private Long savedBookId;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
        Book book = new Book();
        book.setTitle("Spring Boot Guide");
        Book saved = bookRepository.save(book);
        savedBookId = saved.getId();

        // Clear cache before each test
        cacheManager.getCache("books").clear();
    }

    @Test
    void testCacheMissAndHitBehavior() {
        // 1. First Request -> Should trigger Database query (Cache Miss)
        Book firstFetch = bookService.getBookById(savedBookId);
        assertThat(firstFetch).isNotNull();

        // 2. Second Request -> Should fetch directly from Cache (No DB log)
        Book secondFetch = bookService.getBookById(savedBookId);
        assertThat(secondFetch).isNotNull();
        assertThat(secondFetch.getTitle()).isEqualTo("Spring Boot Guide");
    }

    @Test
    void testCacheEvictionBehavior() {
        // Populate cache first
        bookService.getBookById(savedBookId);
        assertThat(cacheManager.getCache("books").get(savedBookId)).isNotNull();

        // Delete -> Should evict from cache
        bookService.deleteBook(savedBookId);
        assertThat(cacheManager.getCache("books").get(savedBookId)).isNull();
    }
}