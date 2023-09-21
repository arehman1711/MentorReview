package com.example.mentorreview.controller;

import com.example.mentorreview.service.interfaces.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/review-mentor")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<String> reviewMentor(
            @RequestParam Long userId,
            @RequestParam Long mentorId,
            @RequestParam String review,
            @RequestParam int rating


    ) {
        log.info("Received review for mentor ID: {}", mentorId);
        try {
            reviewService.reviewMentor(userId, mentorId,rating, review);

            return ResponseEntity.ok("Review submitted successfully");
        } catch (Exception e) {

            return ResponseEntity.badRequest().body("Failed to submit review");
        }
    }
}
