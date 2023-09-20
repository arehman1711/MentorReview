package com.example.mentorreview.service.impl;

import com.example.mentorreview.entity.Mentor;
import com.example.mentorreview.entity.Rating;
import com.example.mentorreview.repository.MentorRepository;
import com.example.mentorreview.repository.RatingRepository;
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
    @Autowired
    RatingRepository ratingRepository;

    @Override
    public List<Mentor> getMentorsByRating(int rating) {
        List<Mentor> mentors = mentorRepository.findByOverallRatingWithReviews(rating);

        // Log the mentors found by overall rating
        log.info("Mentors found with overall rating {}", mentors);

        return mentors;
    }

    @Override
    public Mentor rateMentor(Long mentorId, int rating) {
        // Find the mentor by ID
        Mentor mentor = mentorRepository.findById(mentorId)
                .orElseThrow(() -> new EntityNotFoundException("Mentor not found"));

        // Ensure rating is within the valid range (1 to 5)
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }

        // Log information about the mentor being rated
        log.info("Rating mentor with ID {}: Rating: {}", mentorId, rating);

        // Create a new Rating entity and associate it with the mentor
        Rating newRating = new Rating();
        newRating.setRating(rating);
        newRating.setMentor(mentor);

        // Save the new Rating entity
        ratingRepository.save(newRating);

        // Calculate the new overall rating
        double newOverallRating = calculateNewOverallRating(mentor);

        // Log the new rating value
        log.info("New overall rating for mentor with ID {}: {}", mentorId, newOverallRating);

        // Update the mentor's overall rating
        mentor.setOverallRating(newOverallRating);
        mentorRepository.save(mentor);

        return mentor;
    }

    @Override
    public double calculateNewOverallRating(Mentor mentor) {

        List<Rating> mentorRatings = ratingRepository.findByMentor(mentor);

        if (mentorRatings.isEmpty()) {
            // No ratings yet, return the mentor's current overall rating
            return mentor.getOverallRating();
        }

        int totalRatings = mentorRatings.size();
        int sumOfRatings = 0;

        for (Rating rating : mentorRatings) {
            sumOfRatings += rating.getRating();
        }

        // Calculate the new average rating
        double averageRating = (double) sumOfRatings / totalRatings;

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

