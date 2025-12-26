package com.example.online_shop.listener; 

import com.example.online_shop.entity.User; 
import jakarta.persistence.PrePersist; 
import jakarta.persistence.PreUpdate;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.security.crypto.password.PasswordEncoder; 
import org.springframework.stereotype.Component; 

@Component 
public class UserEntityListener {
    
    private static PasswordEncoder passwordEncoder;
    
    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        UserEntityListener.passwordEncoder = passwordEncoder;
    }
    
    
    @PrePersist
    public void hashPasswordOnCreate(User user) {
        hashPassword(user);
    }
    
    @PreUpdate
    public void hashPasswordOnUpdate(User user) {
        hashPassword(user);
    }
    
    private void hashPassword(User user) {
        if (user.getPassword() != null && 
            !user.getPassword().startsWith("$2a$")) { 
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
    }
}