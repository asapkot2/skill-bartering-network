package com.skillbarter.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "barter_transactions", indexes = {
    @Index(name = "idx_transaction_requester", columnList = "requester_id"),
    @Index(name = "idx_transaction_provider", columnList = "provider_id"),
    @Index(name = "idx_transaction_status", columnList = "status"),
    @Index(name = "idx_transaction_created", columnList = "created_at")
})
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BarterTransaction extends BaseEntity {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requester_id", nullable = false)
    private User requester;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requester_skill_id")
    private UserSkill requesterSkill; // What they're offering in exchange
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id", nullable = false)
    private User provider;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_skill_id", nullable = false)
    private UserSkill providerSkill; // What they're providing
    
    @Column(nullable = false)
    private String title;
    
    @Column(name = "transaction_description", columnDefinition = "TEXT")
    private String transactionDescription;
    
    @Column(name = "hours_requested", nullable = false)
    private Integer hoursRequested;
    
    @Column(name = "hours_offered", nullable = false)
    private Integer hoursOffered;
    
    @Column(name = "credits_amount", nullable = false)
    private Integer creditsAmount;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TransactionStatus status = TransactionStatus.PENDING;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false, length = 20)
    private TransactionType transactionType = TransactionType.DIRECT_EXCHANGE;
    
    @Column(name = "scheduled_start_time")
    private LocalDateTime scheduledStartTime;
    
    @Column(name = "scheduled_end_time")
    private LocalDateTime scheduledEndTime;
    
    @Column(name = "actual_start_time")
    private LocalDateTime actualStartTime;
    
    @Column(name = "actual_end_time")
    private LocalDateTime actualEndTime;
    
    @Column(name = "cancellation_reason", columnDefinition = "TEXT")
    private String cancellationReason;
    
    @Column(name = "dispute_reason", columnDefinition = "TEXT")
    private String disputeReason;
    
    @Column(name = "completed_at")
    private LocalDateTime completedAt;
    
    public enum TransactionStatus {
        PENDING,
        ACCEPTED,
        IN_PROGRESS,
        COMPLETED,
        CANCELLED,
        DISPUTED,
        RESOLVED
    }
    
    public enum TransactionType {
        DIRECT_EXCHANGE,  // 1-to-1 skill exchange
        CREDIT_BASED,     // Using time credits
        MULTI_PARTY       // Complex multi-person exchange
    }
}