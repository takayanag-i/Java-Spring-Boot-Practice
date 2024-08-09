package com.example.demo.model;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Course {

    /** コースID */
    @Id
    @Column(name = "course_id", length = 5)
    private String courseId;

    /** コース名 */
    @Column(name = "course_name", length = 63)
    private String courseName;


    @OneToMany(mappedBy = "course")
    private List<Enrollment> enrollments;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Timetable> timeTables;
}
