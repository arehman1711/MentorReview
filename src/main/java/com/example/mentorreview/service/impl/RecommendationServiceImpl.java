package com.example.mentorreview.service.impl;

import com.example.mentorreview.entity.Mentor;
import com.example.mentorreview.entity.RecommendStudent;
import com.example.mentorreview.repository.MentorRepository;
import com.example.mentorreview.repository.RecommendStudentRepository;
import com.example.mentorreview.service.interfaces.RecommendationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.logging.Logger;
@Slf4j
@Service
public class RecommendationServiceImpl implements RecommendationService {
    @Autowired
    private RecommendStudentRepository recommendStudentRepository;
    @Autowired
    MentorRepository mentorRepository;



    @Override
    public RecommendStudent recommendStudent(Long mentorId, Long userId, String recommendationText) {
        // Find the mentor and validate inputs
        Mentor mentor = mentorRepository.findById(mentorId)
                .orElseThrow(() -> {
                    log.error("Mentor not found for ID: {}", mentorId);
                    return new EntityNotFoundException("Mentor not found");
                });

        if (userId == null) {
            log.error("Student id is null.");
            throw new IllegalArgumentException("Student id cannot be null.");
        }

        // Create and save the recommendation with a unique shareable link
        RecommendStudent recommendedStudent = new RecommendStudent();
        recommendedStudent.setMentor(mentor);
        recommendedStudent.setUserId(userId);
        recommendedStudent.setRecommendationText(recommendationText);

        String shareableLink = generateShareableLink();
        recommendedStudent.setShareableLink(shareableLink);

        log.info("Recommendation saved for mentor ID: {}, student ID: {},Letter of appreciation: {}", mentorId, userId,recommendationText);
        return recommendStudentRepository.save(recommendedStudent);
    }

    @Override
    public String getRecommendationByLink(String shareableLink) {
        // Find the recommendation by shareableLink
        RecommendStudent recommendation = recommendStudentRepository.findByShareableLink(shareableLink);

        if (recommendation == null) {
            // Handle the case where the link is not found
            log.error("Recommendation not found for link: {}", shareableLink);
            throw new EntityNotFoundException("Recommendation not found for the provided link");
        }

        log.info("Retrieved recommendation for link: {}", shareableLink);

        // Return the recommendation text
        return recommendation.getRecommendationText();
    }

    private String generateShareableLink() {
        String uniqueLink = UUID.randomUUID().toString();
        return "https://your-website.com/recommendations/" + uniqueLink;
    }
}
