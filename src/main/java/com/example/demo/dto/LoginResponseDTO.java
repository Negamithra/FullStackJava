package com.example.demo.dto;

public class LoginResponseDTO {

    private String message;
    private String token;
    private String role;
    private Long userId;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(String message, String role, String token, Long userId) {
        this.message = message;
        this.role = role;
        this.token = token;
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    
}