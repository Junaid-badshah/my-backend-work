package com.example.book.demo.runner;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BatchRunner implements CommandLineRunner {

    private final JobLauncher jobLauncher;
    private final Job importBooksJob;

    public BatchRunner(JobLauncher jobLauncher, Job importBooksJob) {
        this.jobLauncher = jobLauncher;
        this.importBooksJob = importBooksJob;
    }

    @Override
    public void run(String... args) throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis()) // Unique parameter taake har bar job run ho
                .toJobParameters();

        jobLauncher.run(importBooksJob, jobParameters);
    }
}