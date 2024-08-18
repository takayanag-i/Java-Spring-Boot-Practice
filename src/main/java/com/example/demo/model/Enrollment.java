package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.sql.Timestamp;
import lombok.Data;

/**
 * Entity class for the `enrollment`.
 */
@Data
@Entity
@IdClass(EnrollmentId.class)
public class Enrollment {

    /** student ID */
    @Id
    @Column(length = 4)
    private String studentId;

    /** the course associated with this enrollment */
    @Id
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    /** enrollment date */
    @Column
    private Timestamp enrollmentDate;

    /** if the enrollment is canceled */
    @Column
    private boolean cancelFlag = false;
}
