package com.example.online_shop.service;

import com.example.online_shop.entity.Category;
import java.util.List;

public interface CategoryService {
    
    List<Category> findAll();
    
    List<Category> getAllCategories();
    
    Category getCategoryById(Long id);
    
    Category createCategory(Category category);
    
    Category updateCategory(Long id, Category updatedCategory);
    
    void deleteCategory(Long id);
    
    Category findByName(String name);
    
    boolean existsById(Long id);
    
    List<Category> searchByName(String keyword);
}