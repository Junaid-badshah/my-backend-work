package com.example.book.demo.runner;

import com.example.book.demo.service.BookEventService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BookEventRunner implements CommandLineRunner {

    private final BookEventService bookEventService;

    public BookEventRunner(BookEventService bookEventService) {
        this.bookEventService = bookEventService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("--- Triggering Book Creation Event Test ---");
        bookEventService.createBook("Spring Boot in Action", "978-1617292547");
    }
}