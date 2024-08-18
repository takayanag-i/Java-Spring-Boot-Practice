package com.example.demo.model;

import java.io.Serializable;
import lombok.Data;

/**
 * ID class for the `enrollment`.
 */
@Data
public class EnrollmentId implements Serializable {

    /** student ID */
    private String studentId;

    /** course */
    private Course course;
}
