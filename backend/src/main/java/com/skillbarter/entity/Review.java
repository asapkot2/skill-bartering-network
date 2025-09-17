package com.skillbarter.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "reviews", 
    indexes = {
        @Index(name = "idx_review_transaction", columnList = "transaction_id"),
        @Index(name = "idx_review_reviewee", columnList = "reviewee_id"),
        @Index(name = "idx_review_rating", columnList = "rating")
    },
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"transaction_id", "reviewer_id", "reviewee_id"})
    }
)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Review extends BaseEntity {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id", nullable = false)
    private BarterTransaction transaction;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewer_id", nullable = false)
    private User reviewer;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewee_id", nullable = false)
    private User reviewee;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;
    
    @Column(nullable = false)
    private Integer rating; // 1-5 stars
    
    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String comment;
    
    @Column(name = "would_recommend")
    private Boolean wouldRecommend = true;
}