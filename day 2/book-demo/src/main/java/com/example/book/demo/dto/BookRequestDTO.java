package com.example.book.demo.dto;

import com.example.book.demo.model.Author;
import lombok.Data;

@Data
public class BookRequestDTO {
    private String title;
    private Author author;
    private Double price;
}