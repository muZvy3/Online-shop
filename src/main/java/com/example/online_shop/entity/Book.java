package com.example.online_shop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Название книги обязательно")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Автор обязателен")
    @Column(nullable = false)
    private String author;

    @Column(columnDefinition = "TEXT")
    private String description;

    @NotBlank(message = "ISBN обязателен")
    @Column(unique = true, nullable = false)
    private String isbn;

    @NotNull(message = "Цена обязательна")
    @DecimalMin(value = "0.0", inclusive = false, message = "Цена должна быть больше 0")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @NotNull(message = "Количество на складе обязательно")
    @Column(nullable = false)
    private Integer stockQuantity;

    @Column(name = "image_url")
    private String imageUrl;

    private Integer pages;
    private String publisher;
    private Integer publicationYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    public Book() {}

    public Book(String title, String author, String isbn, BigDecimal price, Integer stockQuantity) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public Long getId() { 
        return id; 
    }
    public void setId(Long id) { 
        this.id = id; 
    }
    public String getTitle() { 
        return title; 
    }
    public void setTitle(String title) { 
        this.title = title; 
    }
    public String getAuthor() { 
        return author; 
    }
    public void setAuthor(String author) { 
        this.author = author; 
    }
    public String getDescription() { 
        return description; 
    }
    public void setDescription(String description) { 
        this.description = description; 
    }
    public String getIsbn() { 
        return isbn; 
    }
    public void setIsbn(String isbn) { 
        this.isbn = isbn; 
    }
    public BigDecimal getPrice() { 
        return price; 
    }
    public void setPrice(BigDecimal price) { 
        this.price = price; 
    }
    public Integer getStockQuantity() { 
        return stockQuantity; 
    }
    public void setStockQuantity(Integer stockQuantity) { 
        this.stockQuantity = stockQuantity; 
    }
    public String getImageUrl() { 
        return imageUrl; 
    }
    public void setImageUrl(String imageUrl) { 
        this.imageUrl = imageUrl; 
    }
    public Integer getPages() { 
        return pages; 
    }
    public void setPages(Integer pages) { 
        this.pages = pages; 
    }
    public String getPublisher() { 
        return publisher; 
    }
    public void setPublisher(String publisher) { 
        this.publisher = publisher; 
    }
    public Integer getPublicationYear() { 
        return publicationYear; 
    }
    public void setPublicationYear(Integer publicationYear) { 
        this.publicationYear = publicationYear; 
    }
    public Category getCategory() { 
        return category; 
    }
    public void setCategory(Category category) { 
        this.category = category; 
    }
    public LocalDateTime getCreatedAt() { 
        return createdAt; 
    }
    public void setCreatedAt(LocalDateTime createdAt) { 
        this.createdAt = createdAt; 
    }
    public LocalDateTime getUpdatedAt() { 
        return updatedAt; 
    }
    public void setUpdatedAt(LocalDateTime updatedAt) { 
        this.updatedAt = updatedAt; 
    }
    public List<Review> getReviews() { 
        return reviews; 
    }
    public void setReviews(List<Review> reviews) { 
        this.reviews = reviews; 
    }
}