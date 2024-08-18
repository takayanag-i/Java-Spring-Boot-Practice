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

    /** student ID */
    @Id
    @Column(name = "student_id", length = 4)
    private String studentId;

    /** name */
    @Column(name = "name", length = 31)
    private String name;

    /** Email adress */
    @Column(name = "email", length = 63)
    private String email;

    /** password */
    @Column(name = "password", length = 63)
    private String password;
}
