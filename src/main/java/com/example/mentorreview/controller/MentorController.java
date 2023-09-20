package com.example.mentorreview.controller;

import com.example.mentorreview.entity.Mentor;
import com.example.mentorreview.service.interfaces.MentorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/mentors")
public class MentorController {
    @Autowired
    private MentorService mentorService;

    @PostMapping("/create")
    public ResponseEntity<Mentor> saveMentor(@RequestBody Mentor mentor) {
        Mentor savedMentor = mentorService.saveMentor(mentor);
//        log.info("Saved mentor with ID: {}", savedMentor.getId()); // Add the log statement
        return ResponseEntity.ok(savedMentor);
    }
}
