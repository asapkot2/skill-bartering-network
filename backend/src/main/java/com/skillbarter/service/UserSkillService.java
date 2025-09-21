package com.skillbarter.service;

import com.skillbarter.dto.UserSkillDto;
import com.skillbarter.entity.*;
import com.skillbarter.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service layer for managing user skills.
 * 
 * In Spring Boot:
 * - @Service marks this as a service component (business logic)
 * - @Transactional ensures database operations are atomic (all succeed or all fail)
 * - @Autowired injects dependencies (Spring manages object creation for us)
 */
@Service
@Transactional
public class UserSkillService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private SkillRepository skillRepository;
    
    @Autowired
    private UserSkillRepository userSkillRepository;
    
    /**
     * Add a skill to a user's profile.
     * This method demonstrates several Spring Boot concepts:
     * - Exception handling for business rules
     * - Entity relationships (User -> UserSkill -> Skill)
     * - DTO pattern for data transfer
     */
    public UserSkillDto addSkillToUser(Long userId, UserSkillDto.CreateUserSkillRequest request) {
        // Step 1: Validate the user exists
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        
        // Step 2: Validate the skill exists
        Skill skill = skillRepository.findById(request.getSkillId())
            .orElseThrow(() -> new RuntimeException("Skill not found with ID: " + request.getSkillId()));
        
        // Step 3: Check if user already has this skill
        if (userSkillRepository.findByUserAndSkill(user, skill).isPresent()) {
            throw new RuntimeException("User already has this skill: " + skill.getName());
        }
        
        // Step 4: Create the UserSkill entity
        UserSkill userSkill = new UserSkill();
        userSkill.setUser(user);
        userSkill.setSkill(skill);
        
        // Parse the skill level from string to enum
        try {
            userSkill.setLevel(UserSkill.SkillLevel.valueOf(request.getLevel().toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid skill level. Must be: BEGINNER, INTERMEDIATE, ADVANCED, or EXPERT");
        }
        
        userSkill.setYearsOfExperience(request.getYearsOfExperience());
        userSkill.setUserSkillDescription(request.getDescription());
        userSkill.setHourlyCredits(request.getHourlyCredits());
        userSkill.setAvailable(true);
        userSkill.setVerified(false); // Admin will verify later
        
        // Step 5: Save to database
        userSkill = userSkillRepository.save(userSkill);
        
        // Step 6: Update skill usage count
        skill.setUsageCount(skill.getUsageCount() + 1);
        skillRepository.save(skill);
        
        // Step 7: Convert to DTO and return
        return convertToDto(userSkill);
    }
    
    /**
     * Get all skills for a specific user
     */
    public List<UserSkillDto> getUserSkills(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        return userSkillRepository.findByUser(user).stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }
    
    /**
     * Find users who have a specific skill available for bartering
     */
    public List<UserSkillDto> findUsersWithSkill(Long skillId) {
        return userSkillRepository.findBySkill(skillRepository.findById(skillId).orElse(null))
            .stream()
            .filter(us -> us.getAvailable()) // Only available skills
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }
    
    /**
     * Toggle skill availability
     */
    public UserSkillDto toggleSkillAvailability(Long userSkillId, Long userId) {
        UserSkill userSkill = userSkillRepository.findById(userSkillId)
            .orElseThrow(() -> new RuntimeException("UserSkill not found"));
        
        // Verify the user owns this skill
        if (!userSkill.getUser().getId().equals(userId)) {
            throw new RuntimeException("You can only modify your own skills");
        }
        
        userSkill.setAvailable(!userSkill.getAvailable());
        userSkill = userSkillRepository.save(userSkill);
        
        return convertToDto(userSkill);
    }
    
    /**
     * Convert entity to DTO - this keeps our internal structure hidden
     * DTOs are what we send to the frontend, entities are internal only
     */
    private UserSkillDto convertToDto(UserSkill userSkill) {
        UserSkillDto dto = new UserSkillDto();
        dto.setId(userSkill.getId());
        dto.setUserId(userSkill.getUser().getId());
        dto.setUsername(userSkill.getUser().getUsername());
        dto.setSkillId(userSkill.getSkill().getId());
        dto.setSkillName(userSkill.getSkill().getName());
        dto.setLevel(userSkill.getLevel().toString());
        dto.setYearsOfExperience(userSkill.getYearsOfExperience());
        dto.setDescription(userSkill.getUserSkillDescription());
        dto.setHourlyCredits(userSkill.getHourlyCredits());
        dto.setAvailable(userSkill.getAvailable());
        dto.setVerified(userSkill.getVerified());
        dto.setRating(userSkill.getRating());
        return dto;
    }
}
