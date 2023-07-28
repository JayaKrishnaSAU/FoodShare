package com.example.foodshare;
public class User {
    private String name;
    private String email;
    private String password;
    private String userType;

    public User(String name, String email, String password, String userType) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    // Add getters and setters for the properties (name, email, password, userType)
    // You can generate them automatically in Android Studio using the Alt + Insert shortcut.
    // Alternatively, you can manually write them like this:

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}


