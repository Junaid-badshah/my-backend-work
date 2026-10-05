package com.example.book_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BookDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookDemoApplication.class, args);
    }
}
