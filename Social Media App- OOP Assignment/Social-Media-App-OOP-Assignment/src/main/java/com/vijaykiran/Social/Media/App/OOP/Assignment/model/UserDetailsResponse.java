package com.vijaykiran.Social.Media.App.OOP.Assignment.model;

public class UserDetailsResponse {
    private String name;
    private Long userID;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserDetailsResponse(String name, Long userID, String email) {
        this.name = name;
        this.userID = userID;
        this.email = email;
    }

    public UserDetailsResponse(String name, Long userID) {
        this.name = name;
        this.userID = userID;
    }

    // Getters and setters
}
