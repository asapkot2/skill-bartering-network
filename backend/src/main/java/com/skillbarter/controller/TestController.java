package com.skillbarter.controller;

import com.skillbarter.entity.User;
import com.skillbarter.repository.UserRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class TestController {
    
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/health")
    public Map<String, String> health() {
        Map<String, String> status = new HashMap<>();
        status.put("status", "UP");
        status.put("message", "API is running!");
        return status;
    }
    
    @GetMapping("/info")
    public Map<String, Object> info() {
        Map<String, Object> info = new HashMap<>();
        info.put("name", "Skill Barter API");
        info.put("version", "1.0");
        info.put("userCount", userRepository.count());
        return info;
    }
    
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    @PostMapping("/users")
    public Map<String, Object> createTestUser() {
        User user = new User();
        user.setUsername("testuser" + System.currentTimeMillis());
        user.setEmail("test" + System.currentTimeMillis() + "@example.com");
        user.setPassword("password123");
        user.setFirstName("Test");
        user.setLastName("User");
        user.addRole(User.Role.USER);
        
        User savedUser = userRepository.save(user);
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User created successfully");
        response.put("userId", savedUser.getId());
        response.put("username", savedUser.getUsername());
        return response;
    }
}
