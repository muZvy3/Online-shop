package com.example.online_shop.controller;

import com.example.online_shop.service.BookService;
import com.example.online_shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private BookService bookService;
    
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("popularBooks", bookService.findPopularBooks());
        model.addAttribute("latestBooks", bookService.findLatestBooks());
        model.addAttribute("categories", categoryService.findAll());
        return "index";
    }
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}