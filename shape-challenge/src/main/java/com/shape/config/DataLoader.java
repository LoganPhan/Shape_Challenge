package com.shape.config;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.shape.repository.UserRepository;
import com.shape.service.dto.Role;
import com.shape.service.dto.User;

@Component
public class DataLoader implements ApplicationRunner {

    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void run(ApplicationArguments args) {
    	Set<String> roles = new LinkedHashSet<>();
    	roles.add(Role.SUPER_ADMIN.name());
    	userRepository.save(User.builder().name("admin").userName("admin").password(passwordEncoder.encode("admin")).roles(roles).build());
    }
}