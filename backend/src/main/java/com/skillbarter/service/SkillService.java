package com.skillbarter.service;

import com.skillbarter.entity.*;
import com.skillbarter.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SkillService {
    
    @Autowired
    private SkillRepository skillRepository;
    
    @Autowired
    private SkillCategoryRepository categoryRepository;
    
    @Autowired
    private UserSkillRepository userSkillRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public Skill createSkill(String name, String description, Long categoryId) {
        // Check if skill already exists
        if (skillRepository.findByName(name).isPresent()) {
            throw new RuntimeException("Skill already exists: " + name);
        }
        
        Skill skill = new Skill();
        skill.setName(name);
        skill.setSkillDescription(description);
        
        if (categoryId != null) {
            SkillCategory category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
            skill.setCategory(category);
        }
        
        return skillRepository.save(skill);
    }
    
    public UserSkill addSkillToUser(Long userId, Long skillId, UserSkill.SkillLevel level, Integer yearsOfExperience) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        Skill skill = skillRepository.findById(skillId)
            .orElseThrow(() -> new RuntimeException("Skill not found"));
        
        // Check if user already has this skill
        if (userSkillRepository.findByUserAndSkill(user, skill).isPresent()) {
            throw new RuntimeException("User already has this skill");
        }
        
        UserSkill userSkill = new UserSkill();
        userSkill.setUser(user);
        userSkill.setSkill(skill);
        userSkill.setLevel(level);
        userSkill.setYearsOfExperience(yearsOfExperience);
        userSkill.setAvailable(true);
        
        // Update skill usage count
        skill.setUsageCount(skill.getUsageCount() + 1);
        skillRepository.save(skill);
        
        return userSkillRepository.save(userSkill);
    }
    
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }
    
    public List<UserSkill> getUserSkills(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        return userSkillRepository.findByUser(user);
    }
    
    public List<UserSkill> findAvailableSkillProviders(Long skillId, Long excludeUserId) {
        return userSkillRepository.findAvailableProvidersForSkill(skillId, excludeUserId);
    }
}
