package com.personal.mp.learning_platform.repository;

import com.personal.mp.learning_platform.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
}