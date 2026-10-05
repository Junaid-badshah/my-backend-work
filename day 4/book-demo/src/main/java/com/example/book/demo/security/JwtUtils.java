package com.example.book.demo.security;

import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

    private final String jwtSecret = "yourSecretKeyMustBeAtLeast32BytesLongForHS256Algorithm";
    private final int jwtExpirationMs = 86400000; // 24 hours

    public String generateToken(String username) {
        // Simple token generation logic
        return "mocked-jwt-token-for-" + username;
    }

    public String getUsernameFromJwtToken(String token) {
        return "user";
    }

    public boolean validateJwtToken(String authToken) {
        return authToken != null && !authToken.isBlank();
    }
}