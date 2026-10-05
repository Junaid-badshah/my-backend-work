package com.example.book.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class BatchJobRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        // Job launch logic here
    }
}