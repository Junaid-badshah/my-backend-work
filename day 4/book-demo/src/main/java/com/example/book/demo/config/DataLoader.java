package com.example.book.demo.config;

import com.example.book.demo.model.DummyNotification;
import com.example.book.demo.repository.DummyNotificationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final DummyNotificationRepository repository;

    public DataLoader(DummyNotificationRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        // App start hote hi 2 dummy notifications database mein add hongi
        repository.save(new DummyNotification("New book added to store!"));
        repository.save(new DummyNotification("Price updated for Book ID #45"));
    }
}