package com.medilabo.apigateway.config;


import com.medilabo.apigateway.jwt_demo.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class InMemoryUserStore {

    private final List<User> users;

    //admin password: admin123
    //doctor password: medilabo25
    public InMemoryUserStore() {
        this.users = List.of(
                new User(1L, "admin", "$2a$10$XfkJsSrMzNiWd2b6j./1oedbzPiiEXgaOBkXHtlxsRwviLrcAwk7u", "admin"),
                new User(2L, "doctor", "$2a$10$e/c2qUqkIwmqDwKeiwlKDeHvw9BULsjcgmuCUntTzN4tcH4EEwGGC", "physician")
        );
    }

    public Optional<User> findByUsername(String username) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
    }
}


