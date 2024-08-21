package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.demo.model.Time;
import com.example.demo.model.TimeId;

public interface TimeRepository
                extends JpaRepository<Time, TimeId>,
                JpaSpecificationExecutor<Time> {
        List<Time> findByCourse_Enrollments_StudentId(String studentId);

        @Query("SELECT t FROM Time t JOIN FETCH t.course c WHERE c.courseId LIKE :courseId AND c.courseName LIKE :courseName AND t.dayOfWeek LIKE :dayOfWeek AND t.period LIKE :period ORDER BY t.dayOfWeek ASC, t.period ASC")
        List<Time> findByCriteria(@Param("courseId") String courseId,
                        @Param("courseName") String courseName,
                        @Param("dayOfWeek") String dayOfWeek,
                        @Param("period") String period);
}
