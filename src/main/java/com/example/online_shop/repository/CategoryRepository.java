package com.example.online_shop.repository;

import com.example.online_shop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}