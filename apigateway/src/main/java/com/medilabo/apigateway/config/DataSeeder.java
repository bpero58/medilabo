package com.medilabo.apigateway.config;

import com.medilabo.apigateway.jwt_demo.User;
import com.medilabo.apigateway.userrepo.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin") == null) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole("admin");
                userRepository.save(admin);
            }

            if (userRepository.findByUsername("doctor") == null) {
                User doctor = new User();
                doctor.setUsername("doctor");
                doctor.setPassword(passwordEncoder.encode("medilabo25"));
                doctor.setRole("physician");
                userRepository.save(doctor);
            }
        };
    }
}


