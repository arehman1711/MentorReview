package com.example.mentorreview.controller;

import com.example.mentorreview.entity.Mentor;
import com.example.mentorreview.service.interfaces.MentorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/get-mentor-details")
public class GetMentorDetailsController {
    @Autowired
    private MentorService mentorService;

    @GetMapping("/byrating/{rating}")
    public ResponseEntity<?> getMentorsByRating(@PathVariable int rating) {
        List<Mentor> mentors = mentorService.getMentorsByRating(rating);

        if (mentors.isEmpty()) {
            // If no mentors are found, return a custom error message
            String errorMessage = "No mentors found with rating " + rating;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }

        // If mentors are found, return them as usual
        return ResponseEntity.ok(mentors);
    }
}



