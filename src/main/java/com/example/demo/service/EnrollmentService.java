package com.example.demo.service;

import com.example.demo.dto.EnrollmentDto;
import com.example.demo.model.Course;
import com.example.demo.model.Enrollment;
import com.example.demo.model.Timetable;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.EnrollmentRepository;
import com.example.demo.exception.InvalidEnrollmentException;
import com.example.demo.constants.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;

    @Transactional
    public void enroll(EnrollmentDto dto) throws InvalidEnrollmentException {
        // 重複チェック
        Course targetCourse = courseRepository.findById(dto.getCourseId()).orElse(null);
        if (targetCourse == null) {
            throw new InvalidEnrollmentException(ErrorMessages.DRIVER_NO_SUCH_COURSE);
        }

        List<Course> enrolledCourses =
                courseRepository.findByEnrollments_StudentId(dto.getStudentId());
        if (isDuplicateEnrollment(targetCourse, enrolledCourses)) {
            throw new InvalidEnrollmentException(ErrorMessages.DUPLICATE_ENROLLMENT);
        }

        Enrollment entity = convert(dto, targetCourse);

        // インサート
        try {
            enrollmentRepository.save(entity);
        } catch (DataAccessException e) {
            throw new RuntimeException(ErrorMessages.DRIVER_ENROLLMENT_ERROR, e);
        }
    }

    private Enrollment convert(EnrollmentDto dto, Course course) {
        Enrollment entity = new Enrollment();
        entity.setStudentId(dto.getStudentId());
        entity.setCourse(course);
        entity.setEnrollmentDate(dto.getEnrollmentDate());
        return entity;
    }

    private boolean isDuplicateEnrollment(Course targetCourse,
            List<Course> enrolledCourses) {
        for (Course enrolledCourse : enrolledCourses) {
            for (Timetable targetTimeTable : targetCourse.getTimeTables()) {
                for (Timetable enrolledTimeTable : enrolledCourse.getTimeTables()) {
                    if (targetTimeTable.getDayOfWeek().equals(enrolledTimeTable.getDayOfWeek())
                            && targetTimeTable.getPeriod().equals(enrolledTimeTable.getPeriod())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
