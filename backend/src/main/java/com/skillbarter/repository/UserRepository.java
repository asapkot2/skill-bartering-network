package com.skillbarter.repository;

import com.skillbarter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsername(String username);

  Optional<User> findByEmail(String email);

  boolean existsByUsername(String username);

  boolean existsByEmail(String email);

  @Query("SELECT u FROM User u WHERE u.status = :status ORDER BY u.rating DESC")
  List<User> findTopRatedUsersByStatus(@Param("status") User.UserStatus status);
    
  @Query("SELECT u FROM User u JOIN u.skills us WHERE us.skill.id = :skillId AND us.available = true")
  List<User> findUsersWithAvailableSkill(@Param("skillId") Long skillId);
    
  List<User> findByLocationContainingIgnoreCase(String location);
  
}
