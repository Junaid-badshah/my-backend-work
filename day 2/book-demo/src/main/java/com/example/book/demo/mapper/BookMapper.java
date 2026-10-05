package com.example.book.demo.mapper;

import com.example.book.demo.dto.BookRequestDTO;
import com.example.book.demo.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book toEntity(BookRequestDTO dto) {
        if (dto == null) return null;
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setPrice(dto.getPrice());
        return book;
    }

    public BookRequestDTO toDto(Book book) {
        if (book == null) return null;
        BookRequestDTO dto = new BookRequestDTO();
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setPrice(book.getPrice());
        return dto;
    }
}