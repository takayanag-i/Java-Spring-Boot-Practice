package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

/**
 * コマエンティティクラス
 */
@Data
@Entity
@IdClass(TimeId.class)
public class Time {

    /** 講座ID */
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    /** 曜日 */
    @Id
    @Column(length = 1)
    private String dayOfWeek;

    /** 時限 */
    @Id
    @Column(length = 1)
    private String period;
}
