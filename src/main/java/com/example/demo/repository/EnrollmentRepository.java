package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Enrollment;
import com.example.demo.model.EnrollmentId;

public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentId> {
    void deleteByStudentIdAndCourseId(String studentId, String courseId);
}
