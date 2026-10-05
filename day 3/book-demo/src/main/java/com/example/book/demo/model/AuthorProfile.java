package com.example.book.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "author_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bio;

    private String website;

    // One Profile belongs to One Author
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", unique = true, nullable = false)
    private Author author;
}