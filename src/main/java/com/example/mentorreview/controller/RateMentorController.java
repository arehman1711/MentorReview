package com.example.mentorreview.controller;

import com.example.mentorreview.service.interfaces.MentorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/rate-mentor")

public class RateMentorController {
    @Autowired
    private MentorService mentorService;

    @PostMapping("/{mentorId}")
    public ResponseEntity<String> rateMentor(
            @PathVariable("mentorId") Long mentorId,
            @RequestParam("rating") int rating
    ) {
        log.info("Received rating for mentor ID {}: {}", mentorId, rating);
        try {
            mentorService.rateMentor(mentorId, rating);
            return ResponseEntity.ok("Mentor rated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to rate mentor");
        }
    }
}

