package com.example.demo;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import reactor.test.StepVerifier;

@DataR2dbcTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testSaveAndFindBook() {
        Book book = new Book(null, "Reactive Spring", "Junaid");

        StepVerifier.create(
                        bookRepository.save(book)
                                .flatMap(saved -> bookRepository.findByAuthor("Junaid").next())
                )
                .expectNextMatches(found -> found.getTitle().equals("Reactive Spring"))
                .verifyComplete();
    }
}