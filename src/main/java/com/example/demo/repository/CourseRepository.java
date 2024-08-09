package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.example.demo.model.Course;

public interface CourseRepository
        extends JpaRepository<Course, String>, JpaSpecificationExecutor<Course> {
    List<Course> findByCourseId(String courseId);

    List<Course> findByEnrollments_StudentId(String studentId);
}
