package com.skillbarter.repository;

import com.skillbarter.entity.Review;
import com.skillbarter.entity.User;
import com.skillbarter.entity.BarterTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    
    Optional<Review> findByTransactionAndReviewer(BarterTransaction transaction, User reviewer);
    
    Page<Review> findByReviewee(User reviewee, Pageable pageable);
    
    List<Review> findByRevieweeOrderByCreatedAtDesc(User reviewee);
    
    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.reviewee = :user")
    Double calculateAverageRatingForUser(@Param("user") User user);
    
    @Query("SELECT COUNT(r) FROM Review r WHERE r.reviewee = :user")
    Long countReviewsForUser(@Param("user") User user);
    
    List<Review> findByRatingGreaterThanEqual(Integer rating);
    
    @Query("SELECT r FROM Review r WHERE r.reviewee = :user AND r.skill.id = :skillId")
    List<Review> findReviewsForUserSkill(@Param("user") User user, @Param("skillId") Long skillId);
}