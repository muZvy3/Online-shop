package com.example.online_shop.repository;

import com.example.online_shop.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    
    Page<Book> findByCategoryId(Long categoryId, Pageable pageable);
    
    List<Book> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
    
    @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Book> findByTitleContainingIgnoreCase(@Param("title") String title);
    
    @Query("SELECT b FROM Book b WHERE LOWER(b.author) LIKE LOWER(CONCAT('%', :author, '%'))")
    List<Book> findByAuthorContainingIgnoreCase(@Param("author") String author);
    
    @Query("SELECT b FROM Book b LEFT JOIN FETCH b.reviews WHERE b.id = :id")
    Book findByIdWithReviews(@Param("id") Long id);
    
    @Query("SELECT b FROM Book b LEFT JOIN b.reviews r GROUP BY b ORDER BY COUNT(r) DESC")
    Page<Book> findPopularBooks(Pageable pageable);
    
    List<Book> findTop10ByOrderByCreatedAtDesc();
    
    @Query("SELECT b FROM Book b WHERE b.stockQuantity > 0")
    List<Book> findBooksInStock();
}