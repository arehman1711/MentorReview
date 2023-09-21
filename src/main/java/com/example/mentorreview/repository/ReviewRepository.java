package com.example.mentorreview.repository;


import com.example.mentorreview.entity.Mentor;
import com.example.mentorreview.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByMentor(Mentor mentor);
}
