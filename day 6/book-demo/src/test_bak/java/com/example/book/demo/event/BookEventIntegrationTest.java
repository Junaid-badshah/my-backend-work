package com.example.book.demo.event;

import com.example.book.demo.service.BookEventService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
public class BookEventIntegrationTest {

    @Autowired
    private BookEventService bookEventService;

    @Test
    public void testBookCreationTriggersEvents() {
        assertDoesNotThrow(() -> {
            bookEventService.createBook("Domain-Driven Design", "978-0321125217");
        });
    }
}