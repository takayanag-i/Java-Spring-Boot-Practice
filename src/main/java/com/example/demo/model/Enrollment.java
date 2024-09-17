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
 * 履修登録エンティティクラス
 */
@Data
@Entity
@IdClass(EnrollmentId.class)
public class Enrollment {

    /** 出席番号 */
    @Id
    @Column(length = 4)
    private String studentId; // TODO ManyToOne?

    /** 講座 */
    @Id
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    /** 登録日時 */
    @Column
    private Timestamp enrollmentDate;

    /** 「履修取消」フラグ */
    @Column
    private boolean cancelFlag = false;
}
