package com.skillbarter.controller;

import com.skillbarter.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/debug")
public class DebugController {
    
    @Autowired
    private SkillRepository skillRepository;
    
    @Autowired
    private SkillCategoryRepository categoryRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/counts")
    public Map<String, Long> getCounts() {
        Map<String, Long> counts = new HashMap<>();
        counts.put("users", userRepository.count());
        counts.put("categories", categoryRepository.count());
        counts.put("skills", skillRepository.count());
        return counts;
    }
    
    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
