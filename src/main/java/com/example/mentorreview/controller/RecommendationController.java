package com.example.mentorreview.controller;

import com.example.mentorreview.service.interfaces.RecommendationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {
    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/{uuid}")
    public ResponseEntity<?> getRecommendationByLink(@PathVariable String uuid) {
        String shareableLink = "http://localhost:8080/api/recommendations/" + uuid;
        String recommendationText = recommendationService.getRecommendationByLink(shareableLink);
        log.info("recommendationText {}", recommendationText);
        if (recommendationText != null) {
            return ResponseEntity.ok(recommendationText);
        } else {
            // Return a custom error response when the link is not found
            String errorMessage = "Recommendation not found for shareable link: " + shareableLink;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @PostMapping
    public ResponseEntity<String> createRecommendation(@RequestParam Long mentorId, @RequestParam Long userId, @RequestParam String recommendationText) {
        log.info("userId {}", userId);
        try {
            String shareableLink = recommendationService.recommendStudent(mentorId, userId, recommendationText);
            return ResponseEntity.ok("Recommendation created successfully. Shareable Link: " + shareableLink);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to create recommendation");
        }
    }
}


