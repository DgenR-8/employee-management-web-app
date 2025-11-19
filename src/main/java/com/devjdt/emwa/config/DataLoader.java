package com.devjdt.emwa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.devjdt.emwa.entity.User;
import com.devjdt.emwa.repository.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Create admin user if it doesn't exist by username OR email
        if (userRepository.findByUsername("eliboy36").isEmpty() && 
            userRepository.findByEmail("elitabios@gmail.com").isEmpty()) {
            
            User admin = new User();
            admin.setUsername("eliboy36");
            admin.setPassword(passwordEncoder.encode("eli362017"));
            admin.setEmail("elitabios@gmail.com");
            admin.setFirstName("Eli");
            admin.setLastName("Tabios");
            admin.setRole("ADMIN");
            
            userRepository.save(admin);
            System.out.println("Admin user created - Username: eliboy36, Password: eli362017");
        } else {
            System.out.println("Admin user already exists");
        }
        
        // Create regular user if it doesn't exist by username OR email
        if (userRepository.findByUsername("picklerick").isEmpty() && 
            userRepository.findByEmail("picklerick@gmail.com").isEmpty()) {
            
            User eli = new User();
            eli.setUsername("picklerick");
            eli.setPassword(passwordEncoder.encode("picklerick123"));
            eli.setEmail("picklerick@gmail.com");
            eli.setFirstName("Pickle");
            eli.setLastName("Rick");
            eli.setRole("USER");
            
            userRepository.save(eli);
            System.out.println("Regular user created - Username: picklerick, Password: picklerick123");
        } else {
            System.out.println("Regular user already exists");
        }
    }
}