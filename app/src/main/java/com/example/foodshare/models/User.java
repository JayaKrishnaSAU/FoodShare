package com.example.foodshare.models;

public class User {
    private String username;
    private String email;
    private String password;
    private String userType;

    public User(String username, String email, String password, String userType) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    // Getters and setters for the properties
}
