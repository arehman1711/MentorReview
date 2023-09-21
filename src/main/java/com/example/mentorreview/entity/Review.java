package com.example.mentorreview.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rating;

    @Size(max = 50, message = "content must be less than 50 words")
    private String reviewText;

    @ManyToOne
    @JsonIgnore // Add this annotation to break the bidirectional relationship loop
    private Mentor mentor;

    @ManyToOne
    private User user;

}

