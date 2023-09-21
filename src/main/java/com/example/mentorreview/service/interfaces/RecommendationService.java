package com.example.mentorreview.service.interfaces;

import com.example.mentorreview.entity.RecommendStudent;

public interface RecommendationService {

    String recommendStudent(Long mentorId, Long userId, String recommendationText);

    String getRecommendationByLink(String shareableLink);
}
