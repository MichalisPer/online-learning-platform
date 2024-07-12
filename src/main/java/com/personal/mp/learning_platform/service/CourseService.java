package com.personal.mp.learning_platform.service;

import com.personal.mp.learning_platform.exception.ResourceNotFoundException;
import com.personal.mp.learning_platform.model.Course;
import com.personal.mp.learning_platform.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(Long id, Course courseDetails) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            course.setTitle(courseDetails.getTitle());
            course.setDescription(courseDetails.getDescription());
            course.setInstructor(courseDetails.getInstructor());
            return courseRepository.save(course);
        } else {
            throw new ResourceNotFoundException("Course not found with id " + id);
        }
    }

    public void deleteCourse(Long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isPresent()) {
            courseRepository.delete(optionalCourse.get());
        } else {
            throw new ResourceNotFoundException("Course not found with id " + id);
        }
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id " + id));
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}
