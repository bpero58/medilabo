package com.medilabo.apigateway.controller;

import com.medilabo.apigateway.config.InMemoryUserStore;
import com.medilabo.apigateway.jwt_demo.User;
import com.medilabo.apigateway.security.JwtUtil;
import com.medilabo.apigateway.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    InMemoryUserStore userStore;
    @Autowired
    JwtUtil jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid username or password"));
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userStore.findByUsername(userDetails.getUsername()).orElseThrow();
        String token = jwtUtils.generateToken(user.getUsername(), user.getRole());

        return ResponseEntity.ok(Map.of("token", token, "username", user.getUsername(), "role", user.getRole(), "expires_in", 3600));
    }
}




