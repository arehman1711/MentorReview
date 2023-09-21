package com.example.mentorreview.service.interfaces;

import com.example.mentorreview.entity.Mentor;
import java.util.List;

public interface MentorService {
    List<Mentor> getMentorsByRating(int rating);


    public double calculateNewOverallRating(Mentor mentor);

    Mentor saveMentor(Mentor mentor);


}

