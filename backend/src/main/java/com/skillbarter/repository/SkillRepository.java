package com.skillbarter.repository;

import com.skillbarter.entity.Skill;
import com.skillbarter.entity.SkillCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    
    Optional<Skill> findByName(String name);
    
    List<Skill> findByCategory(SkillCategory category);
    
    List<Skill> findByVerifiedTrue();
    
    List<Skill> findByNameContainingIgnoreCase(String keyword);
    
    @Query("SELECT s FROM Skill s ORDER BY s.usageCount DESC")
    List<Skill> findMostPopularSkills();
}