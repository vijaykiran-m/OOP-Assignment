package com.vijaykiran.Social.Media.App.OOP.Assignment.model;

public class CommentUserDetailsResponse {

    private Long userID;
    private String name;

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

    public CommentUserDetailsResponse(Long userID, String name) {

        this.userID = userID;
        this.name = name;

    }

    // Getters and setters
}
