package com.vijaykiran.Social.Media.App.OOP.Assignment.controller;

import com.vijaykiran.Social.Media.App.OOP.Assignment.Repository.CommentRepository;
import com.vijaykiran.Social.Media.App.OOP.Assignment.Repository.PostRepository;
import com.vijaykiran.Social.Media.App.OOP.Assignment.Repository.UserRepository;
import com.vijaykiran.Social.Media.App.OOP.Assignment.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.*;
import java.util.stream.Collectors;

@RestController
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");

        // Find user ID by email
        User userID = userRepository.findByEmail(email);
        if (userID == null) {
            Map<String, String> errors = new HashMap<>();
            errors.put("Error", "User does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
        }

        // Retrieve user by user ID
        User user = userRepository.findById(userID.getUserID()).orElse(null);
        if (user != null && !user.getPassword().equals(password)) {
            Map<String, String> errors = new HashMap<>();
            errors.put("Error", "Username/Password Incorrect");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errors);
        }

        return ResponseEntity.ok("Login Successful");
    }


    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest) {
        String email = userRequest.getEmail();

//         Check if the user already exists
        if (userRepository.existsByEmail(email)) {
            Map<String, String> errors = new HashMap<>();
            errors.put("Error", "Forbidden, Account already exists");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errors);
        }

        String name = userRequest.getName();
        String password = userRequest.getPassword();

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setName(name);
        newUser.setPassword(password);
        userRepository.save(newUser); // Save and flush immediately

        return ResponseEntity.ok("Account Creation Successful");
    }


    @GetMapping("/user")
    public ResponseEntity<?> getUserDetails(@RequestParam Long userID) {
        User user = userRepository.findById(userID).orElse(null);
        if (user == null) {
            Map<String, String> errors = new HashMap<>();
            errors.put("Error", "User does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
        }
        UserDetailsResponse userDetailsResponse = new UserDetailsResponse(user.getName(), user.getUserID(), user.getEmail());
        return ResponseEntity.ok(userDetailsResponse);
    }

    @GetMapping("/")
    public ResponseEntity<List<PostDetailsResponse>> getUserFeed() {
        List<Post> posts = postRepository.findAllByOrderByDateDesc().reversed();
        List<PostDetailsResponse> postDetailsResponses = new ArrayList<>();

        for (Post post : posts) {
            List<CommentDetailsResponse> commentDetailsList = post.getComments().stream()
                    .map(comment -> new CommentDetailsResponse(comment.getCommentID(), comment.getCommentBody(), new CommentUserDetailsResponse(comment.getUser().getUserID(), comment.getUser().getName())))
                    .collect(Collectors.toList());

            PostDetailsResponse postDetailsResponse = new PostDetailsResponse(
                    post.getPostID(),
                    post.getPostBody(),
                    post.getDate(),
                    commentDetailsList
            );

            postDetailsResponses.add(postDetailsResponse);
        }

        return ResponseEntity.ok(postDetailsResponses);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDetailsResponse>> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDetailsResponse> userDetailsResponses = users.stream()
                .map(user -> new UserDetailsResponse(user.getName(), user.getUserID(), user.getEmail()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(userDetailsResponses);
    }

}
