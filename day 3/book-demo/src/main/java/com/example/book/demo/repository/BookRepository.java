package com.example.book.demo.repository;

import com.example.book.demo.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // 1. Standard List Custom JPQL Queries
    @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Book> searchByTitleKeyword(@Param("keyword") String keyword);

    @Query("SELECT b FROM Book b WHERE b.author.name = :authorName")
    List<Book> findBooksByAuthorName(@Param("authorName") String authorName);

    @Query(value = "SELECT * FROM books WHERE price > :minPrice ORDER BY price DESC", nativeQuery = true)
    List<Book> findExpensiveBooksNative(@Param("minPrice") Double minPrice);

    // 2. Paginated Custom JPQL Queries (Notice method signature overload with Pageable)
    @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Book> searchByTitleKeyword(@Param("keyword") String keyword, Pageable pageable);
}