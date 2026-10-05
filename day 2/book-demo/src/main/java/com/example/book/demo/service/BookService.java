package com.example.book.demo.service;

import com.example.book.demo.dto.BookRequestDTO;
import com.example.book.demo.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BookService {

    private final List<Book> bookDatabase = new ArrayList<>();
    private long idCounter = 1;

    public List<Book> getAllBooks() {
        log.info("Fetching all books");
        return bookDatabase;
    }

    public Optional<Book> getBookById(Long id) {
        log.info("Fetching book with id: {}", id);
        return bookDatabase.stream().filter(b -> b.getId().equals(id)).findFirst();
    }

    public Book createBook(BookRequestDTO dto) {
        log.info("Creating new book with title: {}", dto.getTitle());
        Book book = new Book();
        book.setId(idCounter++);
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setPrice(dto.getPrice());
        bookDatabase.add(book);
        return book;
    }

    public Book updateBook(Long id, BookRequestDTO dto) {
        log.info("Updating book with id: {}", id);
        Book existingBook = getBookById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        existingBook.setTitle(dto.getTitle());
        existingBook.setAuthor(dto.getAuthor());
        existingBook.setPrice(dto.getPrice());
        return existingBook;
    }

    public void deleteBook(Long id) {
        log.info("Deleting book with id: {}", id);
        bookDatabase.removeIf(b -> b.getId().equals(id));
    }
}