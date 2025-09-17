package com.skillbarter.repository;

import com.skillbarter.entity.BarterTransaction;
import com.skillbarter.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BarterTransactionRepository extends JpaRepository<BarterTransaction, Long> {
    
    Page<BarterTransaction> findByRequesterOrProvider(User requester, User provider, Pageable pageable);
    
    List<BarterTransaction> findByRequesterAndStatus(User requester, BarterTransaction.TransactionStatus status);
    
    List<BarterTransaction> findByProviderAndStatus(User provider, BarterTransaction.TransactionStatus status);
    
    @Query("SELECT bt FROM BarterTransaction bt WHERE " +
           "(bt.requester = :user OR bt.provider = :user) AND bt.status = :status")
    List<BarterTransaction> findUserTransactionsByStatus(@Param("user") User user, 
                                                         @Param("status") BarterTransaction.TransactionStatus status);
    
    @Query("SELECT bt FROM BarterTransaction bt WHERE bt.status = 'ACCEPTED' " +
           "AND bt.scheduledStartTime BETWEEN :start AND :end " +
           "AND (bt.requester = :user OR bt.provider = :user)")
    List<BarterTransaction> findUpcomingTransactionsForUser(@Param("user") User user,
                                                            @Param("start") LocalDateTime start, 
                                                            @Param("end") LocalDateTime end);
    
    @Query("SELECT COUNT(bt) FROM BarterTransaction bt WHERE " +
           "(bt.requester = :user OR bt.provider = :user) AND bt.status = 'COMPLETED'")
    Long countCompletedTransactionsForUser(@Param("user") User user);
    
    @Query("SELECT bt FROM BarterTransaction bt WHERE bt.status = 'PENDING' " +
           "AND bt.createdAt < :cutoffTime")
    List<BarterTransaction> findExpiredPendingTransactions(@Param("cutoffTime") LocalDateTime cutoffTime);
}