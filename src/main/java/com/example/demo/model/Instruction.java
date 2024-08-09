package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
@IdClass(InstructionId.class)
public class Instruction {

    /** コースID */
    @Id
    @Column(name = "course_id", length = 5)
    private String courseId;

    /** 教員ID */
    @Id
    @Column(name = "instructor_id", length = 5)
    private String instructorId;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;
}
