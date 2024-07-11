package com.personal.mp.learning_platform.repository;

import com.personal.mp.learning_platform.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}