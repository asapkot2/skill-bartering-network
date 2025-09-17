package com.skillbarter.repository;

import com.skillbarter.entity.User;
import com.skillbarter.entity.UserSkill;
import com.skillbarter.entity.Skill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserSkillRepository extends JpaRepository<UserSkill, Long> {
    
    Optional<UserSkill> findByUserAndSkill(User user, Skill skill);
    
    List<UserSkill> findByUser(User user);
    
    List<UserSkill> findBySkill(Skill skill);
    
    List<UserSkill> findByUserAndAvailableTrue(User user);
    
    Page<UserSkill> findByAvailableTrueAndVerifiedTrue(Pageable pageable);
    
    @Query("SELECT us FROM UserSkill us WHERE us.skill.id = :skillId " +
           "AND us.available = true AND us.user.id != :excludeUserId " +
           "ORDER BY us.rating DESC")
    List<UserSkill> findAvailableProvidersForSkill(@Param("skillId") Long skillId, 
                                                   @Param("excludeUserId") Long excludeUserId);
    
    @Query("SELECT us FROM UserSkill us WHERE us.level = :level " +
           "AND us.available = true ORDER BY us.rating DESC")
    List<UserSkill> findBySkillLevel(@Param("level") UserSkill.SkillLevel level);
}