package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * Entity class for the `student`.
 */
@Data
@Entity
public class Student {

    /** The ID of the student. */
    @Id
    @Column(name = "student_id", length = 4)
    private String studentId;

    /** The name of the student. */
    @Column(name = "name", length = 31)
    private String name;

    /** The email adress of the student. */
    @Column(name = "email", length = 63)
    private String email;

    /** The password of the student. */
    @Column(name = "password", length = 63)
    private String password;
}
