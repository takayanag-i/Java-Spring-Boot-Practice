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

    /** instructor ID */
    @Id
    @Column(name = "instructor_id", length = 5)
    private String instructorId;

    /** name */
    @Column(name = "name", length = 31)
    private String name;

    /** Email adress */
    @Column(name = "email", length = 63)
    private String email;

    /** password */
    @Column(name = "password", length = 63)
    private String password;

    /** the list of instructions associated with this instructor */
    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL)
    private List<Instruction> instructions;
}
