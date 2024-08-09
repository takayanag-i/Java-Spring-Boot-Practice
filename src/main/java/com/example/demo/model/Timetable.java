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
@IdClass(TimetableId.class)
public class Timetable {

    /** コースID */
    @Id
    @Column(name = "course_id", length = 5)
    private String courseId;

    /** 曜日 */
    @Id
    @Column(name = "day_of_week", length = 1)
    private String dayOfWeek;

    /** 時限 */
    @Id
    @Column(name = "period", length = 1)
    private String period;

    @ManyToOne
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private Course course;
}
