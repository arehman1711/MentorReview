package com.example.mentorreview.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int rating;
    @ManyToOne
    @JoinColumn(name = "mentor_id")
    @JsonIgnore
    private Mentor mentor;
}
