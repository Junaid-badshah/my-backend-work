package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.demo.service.ReactiveService;

@SpringBootApplication
public class ReactiveDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveDemoApplication.class, args);
    }

    @Bean
    CommandLineRunner run(ReactiveService reactiveService) {
        return args -> {
            System.out.println("--- Executing Reactive Streams ---");
            reactiveService.runMonoExample().subscribe(System.out::println);
            reactiveService.runFluxExample().subscribe(list -> System.out.println("Filtered Tech List: " + list));
            reactiveService.runZipExample().subscribe(System.out::println);
        };
    }
}