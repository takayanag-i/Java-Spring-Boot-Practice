package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.sql.Timestamp;
import lombok.Data;

@Data
@Entity
@IdClass(EnrollmentId.class)
public class Enrollment {

    /** 出席番号 */
    @Id
    @Column(name = "student_id", length = 4)
    private String studentId;

    /** コースID */
    @Id
    @Column(name = "course_id", length = 5)
    private String courseId;

    /** 登録日 */
    @Column(name = "enrollment_date")
    private Timestamp enrollmentDate;

    /** キャンセルフラグ */
    @Column(name = "cancel_flag")
    private boolean cancelFlag;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
