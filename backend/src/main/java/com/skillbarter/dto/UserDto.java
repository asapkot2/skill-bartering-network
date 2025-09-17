package com.skillbarter.dto;

import java.time.LocalDateTime;
import java.util.Set;

public class UserDto {
  private Long id;
  private String username;
  private String email;
  private String firstName;
  private String lastName;
  private String bio;
  private String location;
  private Integer timeCredits;
  private Double rating;
  private Integer completedBarters;
  private Set<String> roles;
  private LocalDateTime createdAt;

  public UserDto() {}

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public String getUsername() { return username; }
  public void setUsername(String username) { this.username = username; }

  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }

  public String getFirstName() { return firstName; }
  public void setFirstName(String firstName) { this.firstName = firstName; }
    
  public String getLastName() { return lastName; }
  public void setLastName(String lastName) { this.lastName = lastName; }
    
  public String getBio() { return bio; }
  public void setBio(String bio) { this.bio = bio; }
    
  public String getLocation() { return location; }
  public void setLocation(String location) { this.location = location; }
    
  public Integer getTimeCredits() { return timeCredits; }
  public void setTimeCredits(Integer timeCredits) { this.timeCredits = timeCredits; }
    
  public Double getRating() { return rating; }
  public void setRating(Double rating) { this.rating = rating; }
    
  public Integer getCompletedBarters() { return completedBarters; }
  public void setCompletedBarters(Integer completedBarters) { this.completedBarters = completedBarters; }
    
  public Set<String> getRoles() { return roles; }
  public void setRoles(Set<String> roles) { this.roles = roles; }
    
  public LocalDateTime getCreatedAt() { return createdAt; }
  public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
  
}
