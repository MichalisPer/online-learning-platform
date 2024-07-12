package com.personal.mp.learning_platform;

import com.personal.mp.learning_platform.model.Enrollment;
import com.personal.mp.learning_platform.repository.EnrollmentRepository;
import com.personal.mp.learning_platform.service.EnrollmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EnrollmentServiceTest {

    @Mock
    private EnrollmentRepository enrollmentRepository;

    @InjectMocks
    private EnrollmentService enrollmentService;

    private Enrollment enrollment;

    @BeforeEach
    public void setUp() {
        enrollment = new Enrollment();
        enrollment.setId(1L);
        // Add other properties if necessary
    }

    @Test
    public void testEnrollStudent() {
        when(enrollmentRepository.save(any(Enrollment.class))).thenReturn(enrollment);

        Enrollment createdEnrollment = enrollmentService.enrollStudent(enrollment);

        assertNotNull(createdEnrollment);
        assertEquals(enrollment.getId(), createdEnrollment.getId());
        verify(enrollmentRepository, times(1)).save(enrollment);
    }

    @Test
    public void testUpdateEnrollment() {
        when(enrollmentRepository.findById(enrollment.getId())).thenReturn(Optional.of(enrollment));
        when(enrollmentRepository.save(any(Enrollment.class))).thenReturn(enrollment);

        Enrollment updatedEnrollment = enrollmentService.updateEnrollment(enrollment.getId(), enrollment);

        assertNotNull(updatedEnrollment);
        assertEquals(enrollment.getId(), updatedEnrollment.getId());
        verify(enrollmentRepository, times(1)).findById(enrollment.getId());
        verify(enrollmentRepository, times(1)).save(enrollment);
    }

    @Test
    public void testDeleteEnrollment() {
        when(enrollmentRepository.findById(enrollment.getId())).thenReturn(Optional.of(enrollment));
        doNothing().when(enrollmentRepository).delete(enrollment);

        enrollmentService.deleteEnrollment(enrollment.getId());

        verify(enrollmentRepository, times(1)).findById(enrollment.getId());
        verify(enrollmentRepository, times(1)).delete(enrollment);
    }

    @Test
    public void testGetEnrollmentById() {
        when(enrollmentRepository.findById(enrollment.getId())).thenReturn(Optional.of(enrollment));

        Enrollment foundEnrollment = enrollmentService.getEnrollmentById(enrollment.getId());

        assertNotNull(foundEnrollment);
        assertEquals(enrollment.getId(), foundEnrollment.getId());
        verify(enrollmentRepository, times(1)).findById(enrollment.getId());
    }

    @Test
    public void testGetAllEnrollments() {
        List<Enrollment> enrollmentList = Collections.singletonList(enrollment);
        when(enrollmentRepository.findAll()).thenReturn(enrollmentList);

        List<Enrollment> enrollments = enrollmentService.getAllEnrollments();

        assertNotNull(enrollments);
        assertEquals(1, enrollments.size());
        verify(enrollmentRepository, times(1)).findAll();
    }
}