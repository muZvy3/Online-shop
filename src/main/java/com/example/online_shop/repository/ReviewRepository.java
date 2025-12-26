package com.example.online_shop.repository;

import com.example.online_shop.entity.Review;
import com.example.online_shop.entity.Book;
import com.example.online_shop.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    
    List<Review> findByBookIdOrderByCreatedAtDesc(Long bookId);
    List<Review> findByUserIdOrderByCreatedAtDesc(Long userId);
    
    Page<Review> findByBookId(Long bookId, Pageable pageable);
    Page<Review> findByUserId(Long userId, Pageable pageable);
    
    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.book.id = :bookId")
    Double findAverageRatingByBookId(@Param("bookId") Long bookId);
    
    @Query("SELECT COUNT(r) FROM Review r WHERE r.book.id = :bookId")
    Long countByBookId(@Param("bookId") Long bookId);
    
    List<Review> findByRatingGreaterThanEqualOrderByCreatedAtDesc(Integer minRating);
    
    Optional<Review> findByUserAndBook(User user, Book book);
    
    @Query("SELECT r FROM Review r JOIN FETCH r.book JOIN FETCH r.user WHERE r.id = :id")
    Optional<Review> findByIdWithDetails(@Param("id") Long id);
    
    @Query("SELECT r.rating, COUNT(r) FROM Review r WHERE r.book.id = :bookId GROUP BY r.rating ORDER BY r.rating DESC")
    List<Object[]> getRatingDistribution(@Param("bookId") Long bookId);
}