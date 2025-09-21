package com.skillbarter.controller;

import com.skillbarter.dto.SkillMatchDto;
import com.skillbarter.service.SkillMatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for finding skill exchange matches.
 */
@RestController
@RequestMapping("/api/matches")
@CrossOrigin(origins = "http://localhost:4200")
public class MatchingController {
    
    @Autowired
    private SkillMatchingService matchingService;
    
    /**
     * Find potential matches for a user
     */
    @GetMapping("/user/{userId}")
    public List<SkillMatchDto> findMatchesForUser(@PathVariable Long userId) {
        return matchingService.findMatchesForUser(userId);
    }
}
