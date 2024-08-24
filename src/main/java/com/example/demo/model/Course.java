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
 * Entity class for the `course`.
 */
@Data
@Entity
@Accessors(chain = true)
public class Course {

    /** The ID of the course. */
    @Id
    @Column(length = 5)
    private String courseId;

    /** The name of the course. */
    @Column(length = 63)
    private String courseName;

    /** The list of enrollments associated with this course. */
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Enrollment> enrollments;

    /** The list of times associated with this course. */
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Time> times;

    /** The list of instructions associated with this course. */
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Instruction> instructions;
}
