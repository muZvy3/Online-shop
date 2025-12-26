package com.example.online_shop.entity;

import com.example.online_shop.listener.UserEntityListener; 
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity 
@Table(name = "users") 
@EntityListeners(UserEntityListener.class) 
public class User {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id; 

    @NotBlank(message = "Email обязателен") 
    @Email(message = "Неверный формат email") 
    @Column(unique = true, nullable = false) 
    private String email; 

    @NotBlank(message = "Пароль обязателен")
    @Column(nullable = false) 
    private String password; 

    @NotBlank(message = "Имя обязательно")
    @Column(name = "first_name", nullable = false) 
    private String firstName; 

    @NotBlank(message = "Фамилия обязательна")
    @Column(name = "last_name", nullable = false)
    private String lastName; 

    @Column(name = "phone_number") 
    private String phoneNumber; 

    @Enumerated(EnumType.STRING) 
    @Column(nullable = false) 
    private UserRole role = UserRole.ROLE_USER; 

    @CreationTimestamp 
    @Column(name = "created_at", updatable = false) 
    private LocalDateTime createdAt; 

    @UpdateTimestamp 
    @Column(name = "updated_at")
    private LocalDateTime updatedAt; 

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>(); 

    public User() {} 

    public User(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() { 
        return id; 
    }
    public void setId(Long id) { 
        this.id = id; 
    }
    public String getEmail() { 
        return email; 
    }
    public void setEmail(String email) { 
        this.email = email; 
    }
    public String getPassword() { 
        return password; 
    }
    public void setPassword(String password) { 
        this.password = password; 
    }
    public String getFirstName() { 
        return firstName; 
    }
    public void setFirstName(String firstName) { 
        this.firstName = firstName; 
    }
    public String getLastName() { 
        return lastName; 
    }
    public void setLastName(String lastName) { 
        this.lastName = lastName; 
    }
    public String getPhoneNumber() { 
        return phoneNumber; 
    }
    public void setPhoneNumber(String phoneNumber) { 
        this.phoneNumber = phoneNumber; 
    }
    public UserRole getRole() { 
        return role; 
    }
    public void setRole(UserRole role) { 
        this.role = role; 
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

    public enum UserRole {
        ROLE_USER,  
        ROLE_ADMIN  
    }
}