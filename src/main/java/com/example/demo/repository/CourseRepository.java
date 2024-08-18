package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.example.demo.model.Course;

/**
 * Repository interface for managing {@link Course} entities.
 */
public interface CourseRepository
        extends JpaRepository<Course, String>, JpaSpecificationExecutor<Course> {

    /**
     * Finds a list of courses by the given course ID.
     *
     * @param courseId the course ID to search for
     * @return a list of courses with the specified course ID
     */
    List<Course> findByCourseId(String courseId);

    /**
     * Finds a list of courses in which a student with the given student ID is enrolled.
     *
     * @param studentId the student ID to search for
     * @return a list of courses that the specified student is enrolled in
     */
    List<Course> findByEnrollments_StudentId(String studentId);
}
