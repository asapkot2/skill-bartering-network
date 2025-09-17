package com.skillbarter.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "skill_categories")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = {"skills"})
@ToString(callSuper = true, exclude = {"skills"})
public class SkillCategory extends BaseEntity {
    
    @Column(unique = true, nullable = false, length = 100)
    private String name;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String categoryDescription;  // Renamed to avoid conflict
    
    @Column(name = "icon_url", length = 500)
    private String iconUrl;
    
    @Column(name = "display_order")
    private Integer displayOrder = 0;
    
    @Column(nullable = false)
    private Boolean active = true;
    
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Skill> skills = new ArrayList<>();
}