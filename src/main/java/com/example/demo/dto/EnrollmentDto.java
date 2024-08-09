package com.example.demo.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class EnrollmentDto {
    private String studentId;
    private String courseId;
    private Timestamp enrollmentDate;
}
