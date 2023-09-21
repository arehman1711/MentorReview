package com.example.mentorreview.service.impl;

import com.example.mentorreview.entity.Mentor;
import com.example.mentorreview.entity.Review;
import com.example.mentorreview.repository.MentorRepository;
import com.example.mentorreview.repository.ReviewRepository;
import com.example.mentorreview.repository.UserRepository;
import com.example.mentorreview.service.interfaces.MentorService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Slf4j
@Transactional
@Service
public class MentorServiceImpl implements MentorService {
    @Autowired
    private MentorRepository mentorRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ReviewRepository reviewRepository;


    @Override
    public List<Mentor> getMentorsByRating(int rating) {
        List<Mentor> mentors = mentorRepository.findByOverallRatingWithReviews(rating);

        // Log the mentors found by overall rating
        log.info("Mentors found with overall rating {}", mentors);

        return mentors;
    }


    @Override
    public double calculateNewOverallRating(Mentor mentor) {
        List<Review> mentorReviews = reviewRepository.findByMentor(mentor);

        if (mentorReviews.isEmpty()) {
            // No reviews yet, return the mentor's current overall rating
            return mentor.getOverallRating();
        }

        int totalReviews = mentorReviews.size();
        int sumOfRatings = 0;

        for (Review review : mentorReviews) {
            sumOfRatings += review.getRating();
        }

        // Calculate the new average rating
        double averageRating = (double) sumOfRatings / totalReviews;

        // Round the average rating to the nearest whole number
        return Math.round(averageRating);
    }
    @Override
    public Mentor saveMentor(Mentor mentor) {
        mentorRepository.save(mentor);
        log.info("successfully saved mentor{}",mentor);
        return mentor;
    }

}

