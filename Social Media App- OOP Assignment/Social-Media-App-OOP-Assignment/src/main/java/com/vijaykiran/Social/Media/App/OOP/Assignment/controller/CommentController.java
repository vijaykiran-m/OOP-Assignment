package com.vijaykiran.Social.Media.App.OOP.Assignment.controller;

import com.vijaykiran.Social.Media.App.OOP.Assignment.Repository.CommentRepository;
import com.vijaykiran.Social.Media.App.OOP.Assignment.Repository.PostRepository;
import com.vijaykiran.Social.Media.App.OOP.Assignment.Repository.UserRepository;
import com.vijaykiran.Social.Media.App.OOP.Assignment.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @PostMapping("/comment")
    public ResponseEntity<?> createComment(@RequestBody CommentRequest commentRequest) {
        String commentBody = commentRequest.getCommentBody();
        Long postID = commentRequest.getPostID();
        Long userID = commentRequest.getUserID();

        // Check if the user exists
        Optional<User> userOptional = userRepository.findById(userID);
        if (userOptional.isEmpty()) {
            Map<String, String> errors = new HashMap<>();
            errors.put("Error", "User does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
        }

        // Check if the post exists
        Optional<Post> postOptional = postRepository.findById(postID);
        if (postOptional.isEmpty()) {
            Map<String, String> errors = new HashMap<>();
            errors.put("Error", "Post does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
        }

        User user = userOptional.get();
        Post post = postOptional.get();

        // Create a new comment
        Comment comment = new Comment();
        comment.setCommentBody(commentBody);
        comment.setUser(user);
        comment.setPost(post);

        commentRepository.save(comment);

        return ResponseEntity.ok("Comment created successfully");
    }
    @GetMapping("/comment")
    public ResponseEntity<?> getCommentDetails(@RequestParam Long commentID) {
        Optional<Comment> commentOptional = commentRepository.findById(commentID);
        if (commentOptional.isEmpty()) {
            Map<String, String> errors = new HashMap<>();
            errors.put("Error", "Comment does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
        }

        Comment comment = commentOptional.get();
        CommentUserDetailsResponse commentCreator = new CommentUserDetailsResponse(comment.getUser().getUserID(), comment.getUser().getName());

        CommentDetailsResponse commentDetailsResponse = new CommentDetailsResponse(
                comment.getCommentID(),
                comment.getCommentBody(),
                commentCreator
        );

        return ResponseEntity.ok(commentDetailsResponse);
    }

    @PatchMapping("/comment")
    public ResponseEntity<?> editComment(@RequestBody CommentEditRequest editRequest) {
        Optional<Comment> commentOptional = commentRepository.findById(editRequest.getCommentID());
        if (commentOptional.isEmpty()) {
            Map<String, String> errors = new HashMap<>();
            errors.put("Error", "Comment does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
        }

        Comment comment = commentOptional.get();
        String newCommentBody = editRequest.getCommentBody();
        comment.setCommentBody(newCommentBody);
        commentRepository.save(comment);

        return ResponseEntity.ok("Comment edited successfully");
    }

    @DeleteMapping("/comment")
    public ResponseEntity<?> deleteComment(@RequestParam Long commentID) {
        Optional<Comment> commentOptional = commentRepository.findById(commentID);
        if (commentOptional.isEmpty()) {
            Map<String, String> errors = new HashMap<>();
            errors.put("Error", "Comment does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
        }

        commentRepository.deleteById(commentID);

        return ResponseEntity.ok("Comment deleted");
    }



}
