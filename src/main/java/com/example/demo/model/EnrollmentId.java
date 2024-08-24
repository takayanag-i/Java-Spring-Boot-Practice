package com.example.demo.model;

import java.io.Serializable;
import lombok.Data;

/**
 * ID class for the `enrollment`.
 */
@Data
public class EnrollmentId implements Serializable {

    private String studentId;
    private Course course;
}
