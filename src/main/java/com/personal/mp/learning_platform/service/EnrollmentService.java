package com.personal.mp.learning_platform.service;

import com.personal.mp.learning_platform.exception.ResourceNotFoundException;
import com.personal.mp.learning_platform.model.Enrollment;
import com.personal.mp.learning_platform.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {

    private EnrollmentRepository enrollmentRepository;

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public Enrollment enrollStudent(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    public Enrollment updateEnrollment(Long id, Enrollment enrollmentDetails) {
        Optional<Enrollment> optionalEnrollment = enrollmentRepository.findById(id);
        if (optionalEnrollment.isPresent()) {
            Enrollment enrollment = optionalEnrollment.get();
            enrollment.setCourse(enrollmentDetails.getCourse());
            enrollment.setStudent(enrollmentDetails.getStudent());
            enrollment.setEnrollmentDate(enrollmentDetails.getEnrollmentDate());
            return enrollmentRepository.save(enrollment);
        } else {
            throw new ResourceNotFoundException("Enrollment not found with id " + id);
        }
    }

    public void deleteEnrollment(Long id) {
        Optional<Enrollment> optionalEnrollment = enrollmentRepository.findById(id);
        if (optionalEnrollment.isPresent()) {
            enrollmentRepository.delete(optionalEnrollment.get());
        } else {
            throw new ResourceNotFoundException("Enrollment not found with id " + id);
        }
    }

    public Enrollment getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with id " + id));
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }
}
