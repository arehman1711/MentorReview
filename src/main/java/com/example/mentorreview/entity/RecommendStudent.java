package com.example.mentorreview.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "RecommendStudent")
public class RecommendStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id") // Map the user_id column in the database
    private Long userId;

    private String shareableLink;

    @Size( max = 500, message = "content must be less than 500 words")// Define an appropriate length for the recommendation text
    private String recommendationText; // This field stores the recommendation text

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    @JsonIgnore
    private Mentor mentor;



    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @JsonIgnore // Add this annotation to prevent User from being serialized
    private User user;


}