package com.skillbarter.controller;

import com.skillbarter.dto.UserSkillDto;
import com.skillbarter.service.UserSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * REST Controller for user skills.
 * 
 * Key Spring Boot annotations:
 * - @RestController: Combines @Controller and @ResponseBody
 * - @RequestMapping: Base URL for all endpoints in this controller
 * - @CrossOrigin: Allows Angular app (on port 4200) to call these APIs
 */
@RestController
@RequestMapping("/api/user-skills")
@CrossOrigin(origins = "http://localhost:4200")
public class UserSkillController {
    
    @Autowired
    private UserSkillService userSkillService;
    
    /**
     * POST /api/user-skills/user/{userId}
     * Add a new skill to a user's profile
     * 
     * @PathVariable: Extracts {userId} from the URL
     * @RequestBody: Converts JSON request body to Java object
     * @Valid: Triggers validation based on annotations in DTO
     */
    @PostMapping("/user/{userId}")
    public ResponseEntity<UserSkillDto> addSkillToUser(
            @PathVariable Long userId,
            @Valid @RequestBody UserSkillDto.CreateUserSkillRequest request) {
        try {
            UserSkillDto created = userSkillService.addSkillToUser(userId, request);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * GET /api/user-skills/user/{userId}
     * Get all skills for a specific user
     */
    @GetMapping("/user/{userId}")
    public List<UserSkillDto> getUserSkills(@PathVariable Long userId) {
        return userSkillService.getUserSkills(userId);
    }
    
    /**
     * GET /api/user-skills/skill/{skillId}
     * Find all users who have a specific skill
     */
    @GetMapping("/skill/{skillId}")
    public List<UserSkillDto> getUsersWithSkill(@PathVariable Long skillId) {
        return userSkillService.findUsersWithSkill(skillId);
    }
    
    /**
     * PUT /api/user-skills/{id}/toggle-availability
     * Toggle whether a skill is available for bartering
     * 
     * @RequestParam: Extracts query parameters (?userId=123)
     */
    @PutMapping("/{id}/toggle-availability")
    public ResponseEntity<UserSkillDto> toggleAvailability(
            @PathVariable Long id,
            @RequestParam Long userId) {
        try {
            UserSkillDto updated = userSkillService.toggleSkillAvailability(id, userId);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
