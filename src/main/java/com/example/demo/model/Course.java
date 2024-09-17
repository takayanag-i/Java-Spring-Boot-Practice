package com.example.demo.model;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 講座エンティティクラス
 */
@Data
@Entity
@Accessors(chain = true)
public class Course {

    /** 講座ID */
    @Id
    @Column(length = 5)
    private String courseId;

    /** 講座名 */
    @Column(length = 63)
    private String courseName;

    /** 講座に紐づけられた履修登録のリスト */
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Enrollment> enrollments;

    /** 講座に紐づけられたコマのリスト */
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Time> times;

    /** 講座に紐づけられた講座担当のリスト */
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Instruction> instructions;
}
