package com.skillbarter.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;

public class UserSkillDto {
  private Long id;
  private Long userId;
  private String username;
  private Long skillId;
  private String skillName;
  private String level;
  private Integer yearsOfExperience;
  private String description;
  private Integer hourlyCredits;
  private Boolean available;
  private Boolean verified;
  private Double rating;

  public static class CreateUserSkillRequest {
    @NotNull(message = "Skill ID is required")
    private Long skillId;

    @NotNull(message = "Level is required")
    private String level;

    @Min(0)
    @Max(50)
    private Integer yearsOfExperience;

    private String description;

    @Min(1)
    @Max(10)
    private Integer hourlyCredits = 1;

    public Long getSkillId() { return skillId; }
    public void setSkillId(Long skillId) { this.skillId = skillId; }
        
    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }
        
    public Integer getYearsOfExperience() { return yearsOfExperience; }
    public void setYearsOfExperience(Integer years) { this.yearsOfExperience = years; }
        
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
        
    public Integer getHourlyCredits() { return hourlyCredits; }
    public void setHourlyCredits(Integer credits) { this.hourlyCredits = credits; }
  }

  // all getters and setters for userskilldto
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  
  public Long getUserId() { return userId; }
  public void setUserId(Long userId) { this.userId = userId; }
  
  public String getUsername() { return username; }
  public void setUsername(String username) { this.username = username; }
  
  public Long getSkillId() { return skillId; }
  public void setSkillId(Long skillId) { this.skillId = skillId; }
  
  public String getSkillName() { return skillName; }
  public void setSkillName(String skillName) { this.skillName = skillName; }
  
  public String getLevel() { return level; }
  public void setLevel(String level) { this.level = level; }
  
  public Integer getYearsOfExperience() { return yearsOfExperience; }
  public void setYearsOfExperience(Integer years) { this.yearsOfExperience = years; }
  
  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }
  
  public Integer getHourlyCredits() { return hourlyCredits; }
  public void setHourlyCredits(Integer credits) { this.hourlyCredits = credits; }
  
  public Boolean getAvailable() { return available; }
  public void setAvailable(Boolean available) { this.available = available; }
  
  public Boolean getVerified() { return verified; }
  public void setVerified(Boolean verified) { this.verified = verified; }
  
  public Double getRating() { return rating; }
  public void setRating(Double rating) { this.rating = rating; }

}