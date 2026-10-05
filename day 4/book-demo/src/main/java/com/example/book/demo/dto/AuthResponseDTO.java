package com.example.book.demo.dto;

public class AuthResponseDTO {
    private String token;

    // No-args constructor
    public AuthResponseDTO() {}

    // Add this All-args constructor
    public AuthResponseDTO(String token) {
        this.token = token;
    }

    // Getters and Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}