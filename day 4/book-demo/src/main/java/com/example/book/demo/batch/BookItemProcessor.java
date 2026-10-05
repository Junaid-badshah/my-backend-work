package com.example.book.demo.batch;

import com.example.book.demo.model.Book;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class BookItemProcessor implements ItemProcessor<Book, Book> {

    @Override
    public Book process(Book book) throws Exception {
        // Basic processing logic (e.g., uppercase title)
        if (book.getTitle() != null) {
            book.setTitle(book.getTitle().toUpperCase());
        }
        return book;
    }
}