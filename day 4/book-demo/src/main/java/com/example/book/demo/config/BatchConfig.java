package com.example.book.demo.config;

import com.example.book.demo.batch.BookItemProcessor;
import com.example.book.demo.model.Book;
import com.example.book.demo.repository.BookRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {

    @Bean
    public FlatFileItemReader<Book> bookReader() {
        return new FlatFileItemReaderBuilder<Book>()
                .name("bookReader")
                .resource(new ClassPathResource("books.csv"))
                .linesToSkip(1) // Skips CSV header: "title,author,price"
                .delimited()
                .names("title", "author", "price") // Matches 3 CSV columns
                .fieldSetMapper(fieldSet -> {
                    Book book = new Book();
                    book.setTitle(fieldSet.readString("title"));
                    book.setPrice(fieldSet.readBigDecimal("price"));
                    return book;
                })
                .build();
    }

    @Bean
    public ItemWriter<Book> bookWriter(BookRepository bookRepository) {
        return new RepositoryItemWriterBuilder<Book>()
                .repository(bookRepository)
                .methodName("save")
                .build();
    }

    @Bean
    public Step importBooksStep(JobRepository jobRepository,
                                PlatformTransactionManager transactionManager,
                                FlatFileItemReader<Book> bookReader,
                                BookItemProcessor bookProcessor,
                                ItemWriter<Book> bookWriter) {
        return new StepBuilder("importBooksStep", jobRepository)
                .<Book, Book>chunk(10, transactionManager)
                .reader(bookReader)
                .processor(bookProcessor)
                .writer(bookWriter)
                .build();
    }

    @Bean
    public Job importBooksJob(JobRepository jobRepository, Step importBooksStep) {
        return new JobBuilder("importBooksJob", jobRepository)
                .start(importBooksStep)
                .build();
    }
}