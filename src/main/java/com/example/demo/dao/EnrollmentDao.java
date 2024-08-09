package com.example.demo.dao;

import com.example.demo.model.Enrollment;
import com.example.demo.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class EnrollmentDao {

    private final EnrollmentRepository enrollmentRepository;

    @Transactional
    public void insert(Enrollment enrollment) throws DataAccessException {
        enrollment.setEnrollmentDate(new Timestamp(System.currentTimeMillis()));
        enrollmentRepository.save(enrollment);
    }

    @Transactional
    public void delete(String studentId, String courseId) throws DataAccessException {
        enrollmentRepository.deleteByStudentIdAndCourseId(studentId, courseId);
    }
}

