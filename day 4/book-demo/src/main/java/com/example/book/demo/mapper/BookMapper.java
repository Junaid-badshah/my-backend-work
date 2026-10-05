package com.example.book.demo.mapper;

import com.example.book.demo.dto.BookDTO;
import com.example.book.demo.model.Book;

public class BookMapper {

    public static Book toEntity(BookDTO dto) {
        if (dto == null) {
            return null;
        }
        Book book = new Book();
        book.setTitle(dto.getTitle());
        // Double se BigDecimal convert kiya
        if (dto.getPrice() != null) {
            book.setPrice(java.math.BigDecimal.valueOf(dto.getPrice()));
        }
        return book;
    }

    public static BookDTO toDTO(Book book) {
        if (book == null) {
            return null;
        }
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        // BigDecimal se Double convert kiya
        if (book.getPrice() != null) {
            dto.setPrice(book.getPrice().doubleValue());
        }
        if (book.getAuthor() != null) {
            dto.setAuthorName(book.getAuthor().getName());
        }
        return dto;
    }
}