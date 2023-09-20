package com.example.mentorreview.repository;

import com.example.mentorreview.entity.Mentor;;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {
    @Query("SELECT DISTINCT m FROM Mentor m " +
            "LEFT JOIN FETCH m.reviews r " +
            "WHERE m.overallRating = :rating")
    List<Mentor> findByOverallRatingWithReviews(@Param("rating") int rating);

}

