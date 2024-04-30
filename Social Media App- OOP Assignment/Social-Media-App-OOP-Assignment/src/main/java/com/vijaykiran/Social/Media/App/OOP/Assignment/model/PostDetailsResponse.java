package com.vijaykiran.Social.Media.App.OOP.Assignment.model;

import java.util.Date;
import java.util.List;

public class PostDetailsResponse {
    private long postID;
    private String postBody;
    private Date date;
    private List<CommentDetailsResponse> comments;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getPostID() {
        return postID;
    }

    public void setPostID(long postID) {
        this.postID = postID;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public List<CommentDetailsResponse> getComments() {
        return comments;
    }

    public void setComments(List<CommentDetailsResponse> comments) {
        this.comments = comments;
    }

    // Constructors, getters, and setters

    public PostDetailsResponse(long postID, String postBody, Date date,List<CommentDetailsResponse> comments) {
        this.postID = postID;
        this.postBody = postBody;
        this.date = date;
        this.comments = comments;
    }

    // Getters and setters
}

