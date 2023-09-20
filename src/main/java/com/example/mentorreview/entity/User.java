package com.example.mentorreview.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Users")
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

        private Long id;

        private String name;
    @OneToMany(mappedBy = "user")
    @JsonIgnore // Add this annotation to prevent recommendations from being serialized
    private List<RecommendStudent> recommendations;

    }

