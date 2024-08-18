package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

/**
 * Entity class for the `instruction`.
 */
@Data
@Entity
@IdClass(InstructionId.class)
public class Instruction {

    /** course ID */
    @Id
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    /** the course associated with this enrollment */
    @Id
    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;
}
