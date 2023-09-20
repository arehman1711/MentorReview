package com.example.mentorreview.service.interfaces;

import com.example.mentorreview.entity.RecommendStudent;

public interface RecommendationService {

    RecommendStudent recommendStudent(Long mentorId, Long userId, String recommendationText);

    String getRecommendationByLink(String shareableLink);
}
