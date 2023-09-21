package com.example.mentorreview.repository;

import com.example.mentorreview.entity.Mentor;
import com.example.mentorreview.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByMentor(Mentor mentor);
}
