package com.devjdt.emwa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.devjdt.emwa.entity.User;
import com.devjdt.emwa.repository.UserRepository;
import com.devjdt.emwa.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Override
    public User registerUser(User user) {
        // Check if username already exists
        if (usernameExists(user.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        
        // Check if email already exists
        if (emailExists(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        
        // Validate password match
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            throw new RuntimeException("Passwords do not match");
        }
        
        // Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
         user.setRole("USER"); // Ensure all new registrations are regular users

        return userRepository.save(user);
    }
    
    @Override
    public boolean usernameExists(String username) {
        return userRepository.existsByUsername(username);
    }
    
    @Override
    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }
}