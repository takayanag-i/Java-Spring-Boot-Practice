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

    /** course ID */
    @Id
    @Column(length = 5)
    private String courseId;

    /** course name */
    @Column(length = 63)
    private String courseName;

    /** The list of enrollments associated with this course */
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Enrollment> enrollments;

    /** the list of times associated with this course */
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Time> times;

    /** the list of instructions associated with this course */
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Instruction> instructions;
}
