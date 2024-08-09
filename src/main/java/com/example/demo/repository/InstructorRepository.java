package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, String> {
    List<Instructor> findByInstructions_CourseIdIn(List<String> courseIds);

}
