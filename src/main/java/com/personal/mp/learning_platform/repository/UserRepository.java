package com.personal.mp.learning_platform.repository;

import com.personal.mp.learning_platform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}