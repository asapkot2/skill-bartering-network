package com.skillbarter.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    
    @Column(unique = true, nullable = false, length = 50)
    private String username;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    @Column(name = "first_name", length = 100)
    private String firstName;
    
    @Column(name = "last_name", length = 100)
    private String lastName;
    
    @Column(columnDefinition = "TEXT")
    private String bio;
    
    @Column(name = "profile_picture_url", length = 500)
    private String profilePictureUrl;
    
    private String location;
    
    private String timezone;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private UserStatus status = UserStatus.PENDING_VERIFICATION;
    
    @Column(name = "time_credits", nullable = false)
    private Integer timeCredits = 10;
    
    @Column(precision = 3, scale = 2)
    private Double rating = 0.0;
    
    @Column(name = "total_reviews")
    private Integer totalReviews = 0;
    
    @Column(name = "completed_barters")
    private Integer completedBarters = 0;
    
    @Column(name = "email_verified", nullable = false)
    private Boolean emailVerified = false;
    
    @Column(name = "last_active_at")
    private LocalDateTime lastActiveAt;
    
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserSkill> skills = new ArrayList<>();
    
    @OneToMany(mappedBy = "requester", fetch = FetchType.LAZY)
    private List<BarterTransaction> requestedTransactions = new ArrayList<>();
    
    @OneToMany(mappedBy = "provider", fetch = FetchType.LAZY)
    private List<BarterTransaction> providedTransactions = new ArrayList<>();
    
    @OneToMany(mappedBy = "reviewee", fetch = FetchType.LAZY)
    private List<Review> reviewsReceived = new ArrayList<>();
    
    @OneToMany(mappedBy = "reviewer", fetch = FetchType.LAZY)
    private List<Review> reviewsGiven = new ArrayList<>();
    
    public enum UserStatus {
        PENDING_VERIFICATION,
        ACTIVE,
        SUSPENDED,
        BANNED
    }
    
    public enum Role {
        USER,
        MODERATOR,
        ADMIN
    }
    
    // Constructor
    public User() {}
    
    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
    
    public String getProfilePictureUrl() { return profilePictureUrl; }
    public void setProfilePictureUrl(String profilePictureUrl) { this.profilePictureUrl = profilePictureUrl; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public String getTimezone() { return timezone; }
    public void setTimezone(String timezone) { this.timezone = timezone; }
    
    public UserStatus getStatus() { return status; }
    public void setStatus(UserStatus status) { this.status = status; }
    
    public Integer getTimeCredits() { return timeCredits; }
    public void setTimeCredits(Integer timeCredits) { this.timeCredits = timeCredits; }
    
    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }
    
    public Integer getTotalReviews() { return totalReviews; }
    public void setTotalReviews(Integer totalReviews) { this.totalReviews = totalReviews; }
    
    public Integer getCompletedBarters() { return completedBarters; }
    public void setCompletedBarters(Integer completedBarters) { this.completedBarters = completedBarters; }
    
    public Boolean getEmailVerified() { return emailVerified; }
    public void setEmailVerified(Boolean emailVerified) { this.emailVerified = emailVerified; }
    
    public LocalDateTime getLastActiveAt() { return lastActiveAt; }
    public void setLastActiveAt(LocalDateTime lastActiveAt) { this.lastActiveAt = lastActiveAt; }
    
    public Set<Role> getRoles() { return roles; }
    public void setRoles(Set<Role> roles) { this.roles = roles; }
    
    public List<UserSkill> getSkills() { return skills; }
    public void setSkills(List<UserSkill> skills) { this.skills = skills; }
    
    public List<BarterTransaction> getRequestedTransactions() { return requestedTransactions; }
    public void setRequestedTransactions(List<BarterTransaction> requestedTransactions) { 
        this.requestedTransactions = requestedTransactions; 
    }
    
    public List<BarterTransaction> getProvidedTransactions() { return providedTransactions; }
    public void setProvidedTransactions(List<BarterTransaction> providedTransactions) { 
        this.providedTransactions = providedTransactions; 
    }
    
    public List<Review> getReviewsReceived() { return reviewsReceived; }
    public void setReviewsReceived(List<Review> reviewsReceived) { this.reviewsReceived = reviewsReceived; }
    
    public List<Review> getReviewsGiven() { return reviewsGiven; }
    public void setReviewsGiven(List<Review> reviewsGiven) { this.reviewsGiven = reviewsGiven; }
    
    // Helper method
    public void addRole(Role role) {
        if (roles == null) {
            roles = new HashSet<>();
        }
        roles.add(role);
    }
}
