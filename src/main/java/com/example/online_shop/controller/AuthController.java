package com.example.online_shop.controller;

import com.example.online_shop.entity.User;
import com.example.online_shop.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    
    private final UserRepository userRepository;
    
    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "auth/register";
    }
    
    @PostMapping("/register")
    public String registerUser(@Valid User user, BindingResult result, Model model) {
        if (userRepository.existsByEmail(user.getEmail())) {
            result.rejectValue("email", "duplicate", 
                "Пользователь с таким email уже зарегистрирован");
        }
        
        if (result.hasErrors()) {
            return "auth/register";
        }
        
        user.setRole(User.UserRole.ROLE_USER);
        
        userRepository.save(user);
        
        return "redirect:/login?registered";
    }
    
    @GetMapping("/login")
    public String showLoginForm() {
        return "auth/login";
    }
}