package com.example.book.demo.service;

import com.example.book.demo.model.DummyNotification;
import com.example.book.demo.repository.BookRepository;
import com.example.book.demo.repository.DummyNotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookScheduledJobs {

    private static final Logger log = LoggerFactory.getLogger(BookScheduledJobs.class);

    private final BookRepository bookRepository;
    private final DummyNotificationRepository notificationRepository;

    public BookScheduledJobs(BookRepository bookRepository, DummyNotificationRepository notificationRepository) {
        this.bookRepository = bookRepository;
        this.notificationRepository = notificationRepository;
    }

    // Job 1: Har 1 minute par total books count karega
    @Scheduled(cron = "0 * * * * ?")
    public void logTotalBooksCount() {
        long totalBooks = bookRepository.count();
        log.info("[SCHEDULED JOB - 1 MIN] Total books in database: {}", totalBooks);
    }

    // Job 2: Har 10 seconds par dummy table check karega
    @Scheduled(fixedDelay = 10000)
    public void pollNewDatabaseRecords() {
        List<DummyNotification> newRecords = notificationRepository.findByProcessedFalse();

        if (newRecords.isEmpty()) {
            log.info("[POLLER JOB - 10 SEC] No new unprocessed records found.");
            return;
        }

        log.info("[POLLER JOB - 10 SEC] Found {} new record(s) to process:", newRecords.size());

        for (DummyNotification record : newRecords) {
            log.info("Processing record ID: {} | Message: {}", record.getId(), record.getMessage());
            record.setProcessed(true);
            notificationRepository.save(record);
        }
    }
}