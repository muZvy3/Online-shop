package com.example.online_shop.service;

import com.example.online_shop.entity.Category;
import com.example.online_shop.exception.DuplicateCategoryException; 
import com.example.online_shop.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service 
@Transactional 
public class CategoryServiceImpl implements CategoryService {
    
    private final CategoryRepository categoryRepository;
    
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository; 
    }
    
    @Override
    @Transactional(readOnly = true) 
    public List<Category> findAll() {
        return getAllCategories(); 
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Category> getAllCategories() {
        return categoryRepository.findAll(); 
    }
    
    @Override
    @Transactional(readOnly = true)
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Категория с ID " + id + " не найдена"));
        
    }
    
    @Override
    public Category createCategory(Category category) {
        
        Category existing = categoryRepository.findByName(category.getName());
        if (existing != null) {
            
            throw new DuplicateCategoryException(
                "Категория с названием '" + category.getName() + "' уже существует"
            );
        }
        return categoryRepository.save(category); 
    }
    
    @Override
    public Category updateCategory(Long id, Category updatedCategory) {
        
        Category existingCategory = getCategoryById(id);
        
        
        if (!existingCategory.getName().equals(updatedCategory.getName())) {
            
            Category categoryWithSameName = categoryRepository.findByName(updatedCategory.getName());
            
            if (categoryWithSameName != null && !categoryWithSameName.getId().equals(id)) {
                throw new DuplicateCategoryException(
                    "Категория с названием '" + updatedCategory.getName() + "' уже существует"
                );
            }
        }
        
        
        existingCategory.setName(updatedCategory.getName());
        existingCategory.setDescription(updatedCategory.getDescription());
        
        return categoryRepository.save(existingCategory); 
    }
    
    @Override
    public void deleteCategory(Long id) {
        Category category = getCategoryById(id); 
        categoryRepository.delete(category); 
    }
    
    @Override
    @Transactional(readOnly = true)
    public Category findByName(String name) {
        Category category = categoryRepository.findByName(name);
        if (category == null) {
            throw new RuntimeException("Категория '" + name + "' не найдена");
        }
        return category;
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return categoryRepository.existsById(id); 
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Category> searchByName(String keyword) {
        
        List<Category> allCategories = categoryRepository.findAll();
        return allCategories.stream()
            .filter(c -> c.getName().toLowerCase().contains(keyword.toLowerCase()))
            .toList(); 
    }
}