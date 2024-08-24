package com.example.demo.model;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

/**
 * Entity class for the `instructor`.
 */
@Data
@Entity
public class Instructor {

    /** The ID of the instructor. */
    @Id
    @Column(name = "instructor_id", length = 5)
    private String instructorId;

    /** The name of the instructor. */
    @Column(name = "name", length = 31)
    private String name;

    /** The email adress of the instructor. */
    @Column(name = "email", length = 63)
    private String email;

    /** The password of the instructor. */
    @Column(name = "password", length = 63)
    private String password;

    /** The list of instructions associated with this instructor. */
    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL)
    private List<Instruction> instructions;
}
