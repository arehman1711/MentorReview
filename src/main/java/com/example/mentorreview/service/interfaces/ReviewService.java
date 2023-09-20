package com.example.mentorreview.service.interfaces;

public interface ReviewService {
    void reviewMentor(Long userId, Long mentorId, String content);
}
