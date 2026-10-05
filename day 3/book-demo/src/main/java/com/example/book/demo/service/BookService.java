package com.example.book.demo.service;

import com.example.book.demo.dto.BookRequestDTO;
import com.example.book.demo.dto.BookResponseDTO;
import com.example.book.demo.exception.ResourceNotFoundException;
import com.example.book.demo.mapper.BookMapper;
import com.example.book.demo.model.Author;
import com.example.book.demo.model.Book;
import com.example.book.demo.repository.AuthorRepository;
import com.example.book.demo.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Transactional(readOnly = true)
    public List<BookResponseDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BookResponseDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));
        return BookMapper.toDTO(book);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public BookResponseDTO createBook(BookRequestDTO dto) {
        Author author = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id " + dto.getAuthorId()));

        Book book = BookMapper.toEntity(dto, author);
        Book savedBook = bookRepository.save(book);
        return BookMapper.toDTO(savedBook);
    }

    @Transactional(rollbackFor = Exception.class)
    public BookResponseDTO updateBook(Long id, BookRequestDTO dto) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));

        Author author = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id " + dto.getAuthorId()));

        existingBook.setTitle(dto.getTitle());
        existingBook.setPrice(dto.getPrice());
        existingBook.setAuthor(author);

        Book updatedBook = bookRepository.save(existingBook);
        return BookMapper.toDTO(updatedBook);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found with id " + id);
        }
        bookRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<BookResponseDTO> searchBooksByTitle(String keyword) {
        return bookRepository.searchByTitleKeyword(keyword)
                .stream()
                .map(BookMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<BookResponseDTO> getBooksByAuthor(String authorName) {
        return bookRepository.findBooksByAuthorName(authorName)
                .stream()
                .map(BookMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<BookResponseDTO> getExpensiveBooks(Double minPrice) {
        return bookRepository.findExpensiveBooksNative(minPrice)
                .stream()
                .map(BookMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<BookResponseDTO> getAllBooksPaginated(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return bookRepository.findAll(pageable)
                .map(BookMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public Page<BookResponseDTO> searchBooksByTitlePaginated(String keyword, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        return bookRepository.searchByTitleKeyword(keyword, pageable)
                .map(BookMapper::toDTO);
    }

    // Task 6 Demo Method: Rollback Verification
    @Transactional(rollbackFor = Exception.class)
    public void createBatchBooksAndFail(List<BookRequestDTO> dtoList) {
        int count = 0;
        for (BookRequestDTO dto : dtoList) {
            createBook(dto); // Step 1: Saves books
            count++;

            // Simulating a system failure on the 2nd book
            if (count == 2) {
                throw new RuntimeException("Simulated System Failure! Triggering Rollback.");
            }
        }
    }
}