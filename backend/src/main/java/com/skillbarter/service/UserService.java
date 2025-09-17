package com.skillbarter.service;

import com.skillbarter.dto.CreateUserDto;
import com.skillbarter.dto.UserDto;
import com.skillbarter.entity.User;
import com.skillbarter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserMapper userMapper;
    
    public UserDto createUser(CreateUserDto dto) {
        // Check if user already exists
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("Username already exists: " + dto.getUsername());
        }
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists: " + dto.getEmail());
        }
        
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword()); // In production, encrypt this
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.addRole(User.Role.USER);
        
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }
    
    public Optional<UserDto> findByUsername(String username) {
        return userRepository.findByUsername(username)
            .map(userMapper::toDto);
    }
    
    public Optional<UserDto> findById(Long id) {
        return userRepository.findById(id)
            .map(userMapper::toDto);
    }
    
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
            .map(userMapper::toDto)
            .collect(Collectors.toList());
    }
    
    public UserDto updateUser(Long id, String firstName, String lastName, String bio, String location) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        if (firstName != null) user.setFirstName(firstName);
        if (lastName != null) user.setLastName(lastName);
        if (bio != null) user.setBio(bio);
        if (location != null) user.setLocation(location);
        
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }
}
