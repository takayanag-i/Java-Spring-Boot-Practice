package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

/**
 * Entity class for the `time`.
 */
@Data
@Entity
@IdClass(TimeId.class)
public class Time {

    /** The course associated with this time. */
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    /** The day of the week represented as a single-digit numeric string. */
    @Id
    @Column(length = 1)
    private String dayOfWeek;

    /** The period. */
    @Id
    @Column(length = 1)
    private String period;
}
