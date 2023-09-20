package com.example.mentorreview.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "Mentor")
public class Mentor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double overallRating;

    @OneToMany(mappedBy = "mentor", fetch = FetchType.LAZY)
    private List<Review> reviews;

    @Override
    public String toString() {
        return "Mentor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", overallRating=" + overallRating +

                '}';
    }
}
