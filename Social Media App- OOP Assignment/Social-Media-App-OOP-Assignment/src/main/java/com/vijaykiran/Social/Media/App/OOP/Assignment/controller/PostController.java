package com.vijaykiran.Social.Media.App.OOP.Assignment.controller;

import com.vijaykiran.Social.Media.App.OOP.Assignment.Repository.PostRepository;
import com.vijaykiran.Social.Media.App.OOP.Assignment.Repository.UserRepository;
import com.vijaykiran.Social.Media.App.OOP.Assignment.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/post")
    public ResponseEntity<?> createPost(@RequestBody PostRequest postRequest) {
        String postBody = postRequest.getPostBody();
        Long userID = postRequest.getUserID();

        // Check if the user exists
        Optional<User> userOptional = userRepository.findById(userID);
        if (userOptional.isEmpty()) {
            Map<String, String> errors = new HashMap<>();
            errors.put("Error", "User does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
        }

        User user = userOptional.get();

        // Create a new post
        Post post = new Post();
        post.setPostBody(postBody);
        post.setUser(user);
        post.setDate();

        postRepository.save(post);

        return ResponseEntity.ok("Post created successfully");
    }

    @GetMapping("/post")
    public ResponseEntity<?> getPostDetails(@RequestParam Long postID) {
        Optional<Post> postOptional = postRepository.findById(postID);
        if (postOptional.isEmpty()) {
            Map<String, String> errors = new HashMap<>();
            errors.put("Error", "Post does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
        }

        Post post = postOptional.get();
        List<CommentDetailsResponse> commentDetailsList = post.getComments().stream()
                .map(comment -> new CommentDetailsResponse(comment.getCommentID(), comment.getCommentBody(), new CommentUserDetailsResponse(comment.getUser().getUserID(),comment.getUser().getName())))
                .collect(Collectors.toList());

        PostDetailsResponse postDetailsResponse = new PostDetailsResponse(
                post.getPostID(),
                post.getPostBody(),
                post.getDate(),
                commentDetailsList
        );

        return ResponseEntity.ok(postDetailsResponse);
    }


    @PatchMapping("/post")
    public ResponseEntity<?> editPost( @RequestBody EditRequest editRequest) {

        Long postID = editRequest.getPostID();
        Optional<Post> postOptional = postRepository.findById(postID);
        if (postOptional.isEmpty()) {
            Map<String, String> errors = new HashMap<>();
            errors.put("Error", "Post does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
        }

        Post post = postOptional.get();
        post.setPostBody(editRequest.getPostBody());
        postRepository.save(post);

        return ResponseEntity.ok("Post edited successfully");
    }

    @DeleteMapping("/post")
    public ResponseEntity<?> deletePost(@RequestParam Long postID) {
        Optional<Post> postOptional = postRepository.findById(postID);
        if (postOptional.isEmpty()) {
            Map<String, String> errors = new HashMap<>();
            errors.put("Error", "Post does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
        }

        postRepository.deleteById(postID);

        return ResponseEntity.ok("Post deleted");
    }



}