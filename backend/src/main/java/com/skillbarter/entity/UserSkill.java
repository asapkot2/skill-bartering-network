package com.skillbarter.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_skills", 
    indexes = {
        @Index(name = "idx_user_skill_user", columnList = "user_id"),
        @Index(name = "idx_user_skill_skill", columnList = "skill_id"),
        @Index(name = "idx_user_skill_available", columnList = "available")
    },
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "skill_id"})
    }
)
public class UserSkill extends BaseEntity {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private SkillLevel level = SkillLevel.BEGINNER;
    
    @Column(name = "years_of_experience")
    private Integer yearsOfExperience;
    
    @Column(name = "user_skill_description", columnDefinition = "TEXT")
    private String userSkillDescription;
    
    @Column(name = "hourly_credits", nullable = false)
    private Integer hourlyCredits = 1;
    
    @Column(nullable = false)
    private Boolean available = true;
    
    @Column(nullable = false)
    private Boolean verified = false;
    
    @Column(name = "verified_at")
    private LocalDateTime verifiedAt;
    
    @Column(name = "verified_by", length = 50)
    private String verifiedBy;
    
    @Column(precision = 3, scale = 2)
    private Double rating = 0.0;
    
    @Column(name = "total_reviews")
    private Integer totalReviews = 0;
    
    @Column(name = "completed_barters")
    private Integer completedBarters = 0;
    
    public enum SkillLevel {
        BEGINNER,
        INTERMEDIATE,
        ADVANCED,
        EXPERT
    }
    
    // Getters and Setters
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public Skill getSkill() { return skill; }
    public void setSkill(Skill skill) { this.skill = skill; }
    
    public SkillLevel getLevel() { return level; }
    public void setLevel(SkillLevel level) { this.level = level; }
    
    public Integer getYearsOfExperience() { return yearsOfExperience; }
    public void setYearsOfExperience(Integer yearsOfExperience) { this.yearsOfExperience = yearsOfExperience; }
    
    public String getUserSkillDescription() { return userSkillDescription; }
    public void setUserSkillDescription(String description) { this.userSkillDescription = description; }
    
    public Integer getHourlyCredits() { return hourlyCredits; }
    public void setHourlyCredits(Integer hourlyCredits) { this.hourlyCredits = hourlyCredits; }
    
    public Boolean getAvailable() { return available; }
    public void setAvailable(Boolean available) { this.available = available; }
    
    public Boolean getVerified() { return verified; }
    public void setVerified(Boolean verified) { this.verified = verified; }
    
    public LocalDateTime getVerifiedAt() { return verifiedAt; }
    public void setVerifiedAt(LocalDateTime verifiedAt) { this.verifiedAt = verifiedAt; }
    
    public String getVerifiedBy() { return verifiedBy; }
    public void setVerifiedBy(String verifiedBy) { this.verifiedBy = verifiedBy; }
    
    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }
    
    public Integer getTotalReviews() { return totalReviews; }
    public void setTotalReviews(Integer totalReviews) { this.totalReviews = totalReviews; }
    
    public Integer getCompletedBarters() { return completedBarters; }
    public void setCompletedBarters(Integer completedBarters) { this.completedBarters = completedBarters; }
}
