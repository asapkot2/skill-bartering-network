package com.skillbarter.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillbarter.dto.SkillMatchDto;
import com.skillbarter.entity.User;
import com.skillbarter.entity.UserSkill;
import com.skillbarter.repository.UserRepository;
import com.skillbarter.repository.UserSkillRepository;

// service that finds potential skill exchange matches between users
@Service
public class SkillMatchingService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserSkillRepository userSkillRepository;

  // find potential matches for a user
  // if user a has skill x and wants skill y, find a user b who has skill y and wants x
  public List<SkillMatchDto> findMatchesForUser(Long userId) {
    User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));

    List<SkillMatchDto> matches = new ArrayList<>();
    // get all skills this user HAS and is willing to teach
    List<UserSkill> mySkills = userSkillRepository.findByUserAndAvailableTrue(user);

    if (mySkills.isEmpty()) {
      return matches;
    }

    for (UserSkill mySkill : mySkills) {
      //find other users with skill
      List<UserSkill> potentialMatches = userSkillRepository
        .findAvailableProvidersForSkill(mySkill.getSkill().getId(), userId);
      
      for (UserSkill theirSkill : potentialMatches) {
        // check if they might want what i have
        SkillMatchDto match = new SkillMatchDto();
        match.setUserId(user.getId());
        match.setUserName(user.getUsername());
        match.setUserSkillId(mySkill.getId());
        match.setUserSkillName(mySkill.getSkill().getName());

        match.setMatchedUserId(theirSkill.getUser().getId());
        match.setMatchedUserName(theirSkill.getUser().getUsername());
        match.setMatchedSkillId((theirSkill.getId()));
        match.setMatchedSkillName(theirSkill.getSkill().getName());

        // calculate match score based on various factors
        double score = calculateMatchScore(mySkill, theirSkill);
        match.setMatchScore(score);

        matches.add(match);

      }
    }

    // sort by match score 
    matches.sort((a, b) -> Double.compare(b.getMatchScore(), a.getMatchScore()));

    return matches.stream().limit(20).collect(Collectors.toList());
  }

  private double calculateMatchScore(UserSkill skill1, UserSkill skill2) {
    
    double score = 0.0;

    // factor 1
    int levelDiff = Math.abs(skill1.getLevel().ordinal() - skill2.getLevel().ordinal());
    score += (4 - levelDiff) * 0.25; // Max 1.0 point for same level
        
        // Factor 2: User ratings
    Double rating1 = skill1.getRating() != null ? skill1.getRating() : 0.0;
    Double rating2 = skill2.getRating() != null ? skill2.getRating() : 0.0;
    score += (rating1 + rating2) / 10.0; // Max 1.0 point for ratings
    
    // Factor 3: Credit balance (prefer balanced exchanges)
    int creditDiff = Math.abs(skill1.getHourlyCredits() - skill2.getHourlyCredits());
    if (creditDiff == 0) {
        score += 1.0; // Perfect balance
    } else if (creditDiff == 1) {
        score += 0.5; // Small difference is OK
    }
    
    // Factor 4: Verification status
    if (skill1.getVerified() && skill2.getVerified()) {
        score += 0.5; // Both verified
    }
    
    return Math.min(score, 5.0); // Cap at 5.0
  }

  /**
 * Find circular matches (A wants B's skill, B wants C's skill, C wants A's skill)
 * This is more complex but creates interesting multi-party exchanges.
 */
  public List<List<SkillMatchDto>> findCircularMatches(Long userId, int maxDepth) {
      // This would use graph algorithms to find cycles
      // For now, returning empty list - can implement later
      return new ArrayList<>();
  }
  
}
