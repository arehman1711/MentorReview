package com.example.mentorreview.service.interfaces;

public interface ReviewService {
    void reviewMentor(Long userId, Long mentorId, int rating, String content);
}
