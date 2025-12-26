package com.example.online_shop.controller;

import com.example.online_shop.entity.Book;
import com.example.online_shop.service.BookService;
import com.example.online_shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    
    @Autowired
    private BookService bookService;
    
    @Autowired
    private CategoryService categoryService;
    
    @GetMapping
    public String listBooks(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String author,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            Model model) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> booksPage = bookService.filterBooksWithPagination(
            categoryId, minPrice, maxPrice, search, author, pageable);
        
        model.addAttribute("books", booksPage.getContent());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", booksPage.getTotalPages());
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("search", search);
        model.addAttribute("author", author);
        
        return "books/list";
    }
    
    @GetMapping("/{id}")
    public String bookDetails(@PathVariable Long id, Model model) {
        Optional<Book> book = bookService.findById(id);
        if (book.isEmpty()) {
            return "redirect:/books";
        }
        
        model.addAttribute("book", book.get());
        return "books/detail";
    }
    
    @GetMapping("/popular")
    public String popularBooks(Model model) {
        model.addAttribute("books", bookService.findPopularBooks());
        model.addAttribute("pageTitle", "Популярные книги");
        return "books/popular";
    }
    
    @GetMapping("/new")
    public String newBooks(Model model) {
        model.addAttribute("books", bookService.findLatestBooks());
        model.addAttribute("pageTitle", "Новинки");
        return "books/new";
    }
}