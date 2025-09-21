package com.skillbarter.controller;

import com.skillbarter.dto.CreateUserDto;
import com.skillbarter.dto.UserDto;
import com.skillbarter.dto.UserSkillDto;
import com.skillbarter.entity.UserSkill;
import com.skillbarter.service.UserService;
import com.skillbarter.service.UserSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Controller to generate test data for development.
 * This would be removed in production.
 */
@RestController
@RequestMapping("/api/test-data")
@CrossOrigin(origins = "http://localhost:4200")
public class TestDataController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserSkillService userSkillService;
    
    @PostMapping("/generate")
    public Map<String, Object> generateTestData() {
        Map<String, Object> result = new HashMap<>();
        List<UserDto> createdUsers = new ArrayList<>();
        
        // Create test users
        String[][] users = {
            {"alice", "alice@example.com", "Alice", "Anderson", "Java, Python"},
            {"bob", "bob@example.com", "Bob", "Brown", "Guitar, Piano"},
            {"charlie", "charlie@example.com", "Charlie", "Chen", "Spanish, French"},
            {"diana", "diana@example.com", "Diana", "Davis", "Cooking, Baking"},
            {"edward", "edward@example.com", "Edward", "Evans", "Photography, Video Editing"}
        };
        
        Map<String, Long> skillIdMap = new HashMap<>();
        skillIdMap.put("Java", 1L);
        skillIdMap.put("Python", 2L);
        skillIdMap.put("Guitar", 9L);
        skillIdMap.put("Piano", 10L);
        skillIdMap.put("Spanish", 15L);
        skillIdMap.put("French", 16L);
        skillIdMap.put("Cooking", 27L);
        skillIdMap.put("Baking", 23L);
        skillIdMap.put("Photography", 33L);
        skillIdMap.put("Video Editing", 34L);
        
        for (String[] userData : users) {
            try {
                CreateUserDto dto = new CreateUserDto();
                dto.setUsername(userData[0]);
                dto.setEmail(userData[1]);
                dto.setPassword("password123");
                dto.setFirstName(userData[2]);
                dto.setLastName(userData[3]);
                
                UserDto user = userService.createUser(dto);
                createdUsers.add(user);
                
                // Add skills to user
                String[] skills = userData[4].split(", ");
                for (String skillName : skills) {
                    Long skillId = skillIdMap.get(skillName);
                    if (skillId != null) {
                        UserSkillDto.CreateUserSkillRequest request = new UserSkillDto.CreateUserSkillRequest();
                        request.setSkillId(skillId);
                        request.setLevel("INTERMEDIATE");
                        request.setYearsOfExperience(3);
                        request.setDescription("Experienced in " + skillName);
                        request.setHourlyCredits(1);
                        
                        userSkillService.addSkillToUser(user.getId(), request);
                    }
                }
            } catch (Exception e) {
                // User might already exist
            }
        }
        
        result.put("usersCreated", createdUsers.size());
        result.put("users", createdUsers);
        result.put("message", "Test data generated successfully");
        
        return result;
    }
}
