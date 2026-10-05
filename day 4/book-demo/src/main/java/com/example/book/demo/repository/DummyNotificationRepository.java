package com.example.book.demo.repository;

import com.example.book.demo.model.DummyNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DummyNotificationRepository extends JpaRepository<DummyNotification, Long> {
    List<DummyNotification> findByProcessedFalse();
}