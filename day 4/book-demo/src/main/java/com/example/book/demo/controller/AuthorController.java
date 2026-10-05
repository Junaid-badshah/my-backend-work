package com.example.book.demo.controller;

import com.example.book.demo.model.Author;
import com.example.book.demo.model.Book;
import com.example.book.demo.repository.AuthorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @PostMapping
    public Author createAuthor(@RequestBody Author author) {
        return authorRepository.save(author);
    }

    @PostMapping("/{authorId}/books")
    public ResponseEntity<Author> addBookToAuthor(@PathVariable Long authorId, @RequestBody Book book) {
        return authorRepository.findById(authorId).map(author -> {
            author.addBook(book);
            Author updatedAuthor = authorRepository.save(author);
            return ResponseEntity.ok(updatedAuthor);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}