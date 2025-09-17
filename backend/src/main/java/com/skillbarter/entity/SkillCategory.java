package com.skillbarter.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "skill_categories")
public class SkillCategory extends BaseEntity {
    
    @Column(unique = true, nullable = false, length = 100)
    private String name;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String categoryDescription;
    
    @Column(name = "icon_url", length = 500)
    private String iconUrl;
    
    @Column(name = "display_order")
    private Integer displayOrder = 0;
    
    @Column(nullable = false)
    private Boolean active = true;
    
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Skill> skills = new ArrayList<>();
    
    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getCategoryDescription() { return categoryDescription; }
    public void setCategoryDescription(String categoryDescription) { 
        this.categoryDescription = categoryDescription; 
    }
    
    public String getIconUrl() { return iconUrl; }
    public void setIconUrl(String iconUrl) { this.iconUrl = iconUrl; }
    
    public Integer getDisplayOrder() { return displayOrder; }
    public void setDisplayOrder(Integer displayOrder) { this.displayOrder = displayOrder; }
    
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    
    public List<Skill> getSkills() { return skills; }
    public void setSkills(List<Skill> skills) { this.skills = skills; }
}
