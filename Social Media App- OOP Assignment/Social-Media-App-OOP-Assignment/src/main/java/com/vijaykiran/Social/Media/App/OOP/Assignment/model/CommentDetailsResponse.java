package com.vijaykiran.Social.Media.App.OOP.Assignment.model;

public class CommentDetailsResponse {
    private Long commentID;
    private String commentBody;
    private CommentUserDetailsResponse commentCreator;

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public Long getCommentID() {
        return commentID;
    }

    public void setCommentID(Long commentID) {
        this.commentID = commentID;
    }

    public CommentUserDetailsResponse getCommentCreator() {
        return commentCreator;
    }

    public void setCommentCreator(CommentUserDetailsResponse commentCreator) {
        this.commentCreator = commentCreator;
    }



    // Constructors, getters, and setters

    public CommentDetailsResponse(Long commentID, String commentBody, CommentUserDetailsResponse commentCreator) {
        this.commentID = commentID;
        this.commentBody = commentBody;
        this.commentCreator = commentCreator;
    }

    // Getters and setters
}

