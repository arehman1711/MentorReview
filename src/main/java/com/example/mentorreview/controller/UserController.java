package com.example.mentorreview.controller;

import com.example.mentorreview.entity.User;
import com.example.mentorreview.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/users") // Add a base URL mapping
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        // Save the user data to the database
        User savedUser = userRepository.save(user);
        log.info("successfully Saved user{}", user); // Add the log statement
        return ResponseEntity.ok(savedUser);
    }
}

