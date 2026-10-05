package com.example.book.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test & !caffeine")
public class ProfileCheckRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("==========================================");
        System.out.println("Active DB Connection: jdbc:h2:mem:testdb");
        System.out.println("Running on Server Port: 8080");
        System.out.println("==========================================");
    }
}