package com.example.foodshare.models;

public class User {
    private int userId;
    private String username;
    private String email;
    private String password;
    private int userTypeId;

    public User(int userId, String username, String email, String password, int userTypeId) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.userTypeId = userTypeId;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getUserTypeId() {
        return userTypeId;
    }
}
