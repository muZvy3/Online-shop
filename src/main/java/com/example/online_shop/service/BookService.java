package com.example.online_shop.service;

import com.example.online_shop.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    Page<Book> findAll(Pageable pageable);
    Optional<Book> findById(Long id);
    Book save(Book book);
    void deleteById(Long id);
    
    List<Book> findByCategory(Long categoryId);
    List<Book> filterBooks(Long categoryId, BigDecimal minPrice, BigDecimal maxPrice, String search, String author);
    Page<Book> filterBooksWithPagination(Long categoryId, BigDecimal minPrice, BigDecimal maxPrice, String search, String author, Pageable pageable);
    
    List<Book> findAll(Specification<Book> spec);
    Page<Book> findAll(Specification<Book> spec, Pageable pageable);
    
    List<Book> findPopularBooks();
    List<Book> findLatestBooks();
    List<Book> findBooksInStock();
    List<Book> findByPublisher(String publisher);
}