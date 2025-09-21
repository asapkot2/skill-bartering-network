package com.skillbarter.dto;

/**
 * DTO for skill match results.
 * Shows potential exchange opportunities between users.
 */
public class SkillMatchDto {
    private Long userId;
    private String userName;
    private Long userSkillId;
    private String userSkillName;
    
    private Long matchedUserId;
    private String matchedUserName;
    private Long matchedSkillId;
    private String matchedSkillName;
    
    private Double matchScore;
    private String matchType; // DIRECT, CIRCULAR, CREDIT_BASED
    
    // Getters and Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    
    public Long getUserSkillId() { return userSkillId; }
    public void setUserSkillId(Long userSkillId) { this.userSkillId = userSkillId; }
    
    public String getUserSkillName() { return userSkillName; }
    public void setUserSkillName(String userSkillName) { this.userSkillName = userSkillName; }
    
    public Long getMatchedUserId() { return matchedUserId; }
    public void setMatchedUserId(Long matchedUserId) { this.matchedUserId = matchedUserId; }
    
    public String getMatchedUserName() { return matchedUserName; }
    public void setMatchedUserName(String matchedUserName) { this.matchedUserName = matchedUserName; }
    
    public Long getMatchedSkillId() { return matchedSkillId; }
    public void setMatchedSkillId(Long matchedSkillId) { this.matchedSkillId = matchedSkillId; }
    
    public String getMatchedSkillName() { return matchedSkillName; }
    public void setMatchedSkillName(String matchedSkillName) { this.matchedSkillName = matchedSkillName; }
    
    public Double getMatchScore() { return matchScore; }
    public void setMatchScore(Double matchScore) { this.matchScore = matchScore; }
    
    public String getMatchType() { return matchType; }
    public void setMatchType(String matchType) { this.matchType = matchType; }
}
