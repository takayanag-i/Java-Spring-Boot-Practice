package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

/**
 * 講座担当エンティティクラス
 */
@Data
@Entity
@IdClass(InstructionId.class)
public class Instruction {

    /** 講座 */
    @Id
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    /** 教員 */
    @Id
    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;
}
