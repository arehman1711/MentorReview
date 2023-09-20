package com.example.mentorreview.repository;

import com.example.mentorreview.entity.RecommendStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendStudentRepository extends JpaRepository<RecommendStudent, Long> {
    RecommendStudent findByShareableLink(String shareableLink);
}
