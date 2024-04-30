package com.vijaykiran.Social.Media.App.OOP.Assignment.model;

public class CommentRequest {

    private String commentBody;
    private Long postID;
    private Long userID;


    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public Long getPostID() {
        return postID;
    }

    public void setPostID(Long postID) {
        this.postID = postID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public CommentRequest(String commentBody, Long postID, Long userID) {
        this.commentBody = commentBody;
        this.postID = postID;
        this.userID = userID;
    }
}
