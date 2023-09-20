package com.example.mentorreview.repository;

import com.example.mentorreview.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
