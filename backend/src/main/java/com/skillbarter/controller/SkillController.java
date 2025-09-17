package com.skillbarter.controller;

import com.skillbarter.dto.SkillDto;
import com.skillbarter.entity.Skill;
import com.skillbarter.entity.SkillCategory;
import com.skillbarter.repository.SkillCategoryRepository;
import com.skillbarter.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/skills")
@CrossOrigin(origins = "http://localhost:4200")
public class SkillController {
    
    @Autowired
    private SkillRepository skillRepository;
    
    @Autowired
    private SkillCategoryRepository categoryRepository;
    
    @GetMapping
    public List<SkillDto> getAllSkills() {
        return skillRepository.findAll().stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }
    
    @GetMapping("/categories")
    public List<Map<String, Object>> getAllCategories() {
        return categoryRepository.findByActiveTrue().stream()
            .map(cat -> {
                Map<String, Object> map = new HashMap<>();
                map.put("id", cat.getId());
                map.put("name", cat.getName());
                map.put("description", cat.getCategoryDescription());
                return map;
            })
            .collect(Collectors.toList());
    }
    
    @GetMapping("/category/{categoryId}")
    public List<SkillDto> getSkillsByCategory(@PathVariable Long categoryId) {
        SkillCategory category = categoryRepository.findById(categoryId).orElse(null);
        if (category == null) {
            return List.of();
        }
        return skillRepository.findByCategory(category).stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }
    
    @GetMapping("/search")
    public List<SkillDto> searchSkills(@RequestParam String keyword) {
        return skillRepository.findByNameContainingIgnoreCase(keyword).stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<SkillDto> getSkillById(@PathVariable Long id) {
        return skillRepository.findById(id)
            .map(this::convertToDto)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    private SkillDto convertToDto(Skill skill) {
        SkillDto dto = new SkillDto();
        dto.setId(skill.getId());
        dto.setName(skill.getName());
        dto.setDescription(skill.getSkillDescription());
        dto.setCategory(skill.getCategory() != null ? skill.getCategory().getName() : null);
        dto.setUsageCount(skill.getUsageCount());
        dto.setVerified(skill.getVerified());
        return dto;
    }
}
