package com.example.demo.service;

import com.example.demo.repository.EnrollmentRepository;
import com.example.demo.constants.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteService {

    private final EnrollmentRepository enrollmentRepository;

    /**
     * 履修登録を抹消する
     *
     * @param studentId 出席番号
     * @param courseId 講座コード
     */
    @Transactional
    public void deleteEnrollment(String studentId, String courseId) {
        try {
            enrollmentRepository.deleteByStudentIdAndCourseId(studentId, courseId);
        } catch (DataAccessException e) {
            throw new RuntimeException(ErrorMessages.DRIVER_DELETE_ERROR, e);
        }
    }
}
