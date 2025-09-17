package com.skillbarter.service;

import com.skillbarter.dto.UserDto;
import com.skillbarter.entity.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {
    
    public UserDto toDto(User user) {
        if (user == null) return null;
        
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setBio(user.getBio());
        dto.setLocation(user.getLocation());
        dto.setTimeCredits(user.getTimeCredits());
        dto.setRating(user.getRating());
        dto.setCompletedBarters(user.getCompletedBarters());
        dto.setCreatedAt(user.getCreatedAt());
        
        if (user.getRoles() != null) {
            dto.setRoles(user.getRoles().stream()
                .map(Enum::name)
                .collect(Collectors.toSet()));
        }
        
        return dto;
    }
}
