package com.example.online_shop.service.impl;

import com.example.online_shop.entity.Book;
import com.example.online_shop.repository.BookRepository;
import com.example.online_shop.service.BookService;
import com.example.online_shop.specification.BookSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    
    @Autowired
    private BookRepository bookRepository;
    
    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }
    
    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }
    
    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Book> findByCategory(Long categoryId) {
        return bookRepository.findByCategoryId(categoryId, Pageable.unpaged()).getContent();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Book> filterBooks(Long categoryId, BigDecimal minPrice, BigDecimal maxPrice, String search, String author) {
        Specification<Book> spec = BookSpecification.withFilters(categoryId, minPrice, maxPrice, search, author, null);
        return bookRepository.findAll(spec);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<Book> filterBooksWithPagination(Long categoryId, BigDecimal minPrice, BigDecimal maxPrice, String search, String author, Pageable pageable) {
        Specification<Book> spec = BookSpecification.withFilters(categoryId, minPrice, maxPrice, search, author, null);
        return bookRepository.findAll(spec, pageable);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll(Specification<Book> spec) {
        return bookRepository.findAll(spec);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<Book> findAll(Specification<Book> spec, Pageable pageable) {
        return bookRepository.findAll(spec, pageable);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Book> findPopularBooks() {
        return bookRepository.findPopularBooks(Pageable.ofSize(10)).getContent();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Book> findLatestBooks() {
        return bookRepository.findTop10ByOrderByCreatedAtDesc();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Book> findBooksInStock() {
        return bookRepository.findBooksInStock();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Book> findByPublisher(String publisher) {
        Specification<Book> spec = BookSpecification.byPublisher(publisher);
        return bookRepository.findAll(spec);
    }
}