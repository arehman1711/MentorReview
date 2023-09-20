package com.example.mentorreview.service.impl;

import com.example.mentorreview.entity.Mentor;
import com.example.mentorreview.entity.Review;
import com.example.mentorreview.entity.User;
import com.example.mentorreview.repository.MentorRepository;
import com.example.mentorreview.repository.ReviewRepository;
import com.example.mentorreview.repository.UserRepository;
import com.example.mentorreview.service.interfaces.ReviewService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    MentorServiceImpl mentorService;
    @Override
    public void reviewMentor(Long userId, Long mentorId, String content) {
        // Find the user, mentor, and validate rating
        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.error("User not found for ID: {}", userId);
                    return new EntityNotFoundException("User not found");
                });

        Mentor mentor = mentorRepository.findById(mentorId)
                .orElseThrow(() -> {
                    log.error("Mentor not found for ID: {}", mentorId);
                    return new EntityNotFoundException("Mentor not found");
                });

        // Create and save the review
        Review review = new Review();
        review.setUser(user);
        review.setMentor(mentor);
        review.setReviewText(content);

        reviewRepository.save(review);

        // Calculate and update the mentor's overall rating
        double newOverallRating = mentorService.calculateNewOverallRating(mentor);
        mentor.setOverallRating(newOverallRating);
        mentorRepository.save(mentor);

        log.info("Review saved for user ID: {}, mentor ID: {}, review: {}", userId, mentorId,content);
    }
}

