package com.example.book.demo.mapper;

import com.example.book.demo.dto.BookRequestDTO;
import com.example.book.demo.dto.BookResponseDTO;
import com.example.book.demo.model.Author;
import com.example.book.demo.model.Book;

public class BookMapper {

    public static Book toEntity(BookRequestDTO dto, Author author) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setPrice(dto.getPrice());
        book.setAuthor(author); // Set actual Author entity object
        return book;
    }

    public static BookResponseDTO toDTO(Book book) {
        return new BookResponseDTO(
                book.getId(),
                book.getTitle(),
                book.getPrice(),
                book.getAuthor() != null ? book.getAuthor().getName() : null
        );
    }
}