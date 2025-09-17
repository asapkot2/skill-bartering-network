package com.skillbarter.entity;

import javax.persistence.*;

@Entity
@Table(name = "skills", indexes = {
    @Index(name = "idx_skill_name", columnList = "name"),
    @Index(name = "idx_skill_verified", columnList = "verified")
})
public class Skill extends BaseEntity {
    
    @Column(unique = true, nullable = false, length = 100)
    private String name;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String skillDescription;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private SkillCategory category;
    
    @Column(name = "usage_count")
    private Integer usageCount = 0;
    
    @Column(nullable = false)
    private Boolean verified = false;
    
    @Column(name = "icon_url", length = 500)
    private String iconUrl;
    
    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getSkillDescription() { return skillDescription; }
    public void setSkillDescription(String skillDescription) { this.skillDescription = skillDescription; }
    
    public SkillCategory getCategory() { return category; }
    public void setCategory(SkillCategory category) { this.category = category; }
    
    public Integer getUsageCount() { 
        return usageCount != null ? usageCount : 0; 
    }
    public void setUsageCount(Integer usageCount) { this.usageCount = usageCount; }
    
    public Boolean getVerified() { return verified; }
    public void setVerified(Boolean verified) { this.verified = verified; }
    
    public String getIconUrl() { return iconUrl; }
    public void setIconUrl(String iconUrl) { this.iconUrl = iconUrl; }
}
