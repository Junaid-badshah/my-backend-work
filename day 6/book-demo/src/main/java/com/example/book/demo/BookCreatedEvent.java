package com.example.book.demo;

public class BookCreatedEvent {
    private final String title;
    private final String isbn;

    public BookCreatedEvent(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }
}