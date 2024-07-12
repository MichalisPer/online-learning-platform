package com.personal.mp.learning_platform;

import com.personal.mp.learning_platform.model.Course;
import com.personal.mp.learning_platform.repository.CourseRepository;
import com.personal.mp.learning_platform.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService;

    private Course course;

    @BeforeEach
    public void setUp() {
        course = new Course();
        course.setId(1L);
        course.setTitle("Test Course");
        course.setDescription("This is a test course");
        // Add other course properties if necessary
    }

    @Test
    public void testCreateCourse() {
        when(courseRepository.save(any(Course.class))).thenReturn(course);

        Course createdCourse = courseService.createCourse(course);

        assertNotNull(createdCourse);
        assertEquals(course.getTitle(), createdCourse.getTitle());
        verify(courseRepository, times(1)).save(course);
    }

    @Test
    public void testUpdateCourse() {
        when(courseRepository.findById(course.getId())).thenReturn(Optional.of(course));
        when(courseRepository.save(any(Course.class))).thenReturn(course);

        Course updatedCourse = courseService.updateCourse(course.getId(), course);

        assertNotNull(updatedCourse);
        assertEquals(course.getTitle(), updatedCourse.getTitle());
        verify(courseRepository, times(1)).findById(course.getId());
        verify(courseRepository, times(1)).save(course);
    }

    @Test
    public void testDeleteCourse() {
        when(courseRepository.findById(course.getId())).thenReturn(Optional.of(course));
        doNothing().when(courseRepository).delete(course);

        courseService.deleteCourse(course.getId());

        verify(courseRepository, times(1)).findById(course.getId());
        verify(courseRepository, times(1)).delete(course);
    }

    @Test
    public void testGetCourseById() {
        when(courseRepository.findById(course.getId())).thenReturn(Optional.of(course));

        Course foundCourse = courseService.getCourseById(course.getId());

        assertNotNull(foundCourse);
        assertEquals(course.getTitle(), foundCourse.getTitle());
        verify(courseRepository, times(1)).findById(course.getId());
    }

    @Test
    public void testGetAllCourses() {
        List<Course> courseList = Collections.singletonList(course);
        when(courseRepository.findAll()).thenReturn(courseList);

        List<Course> courses = courseService.getAllCourses();

        assertNotNull(courses);
        assertEquals(1, courses.size());
        verify(courseRepository, times(1)).findAll();
    }
}
