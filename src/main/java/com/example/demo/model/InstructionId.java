package com.example.demo.model;

import java.io.Serializable;
import lombok.Data;

/**
 * ID class for the `instruction`.
 */
@Data
public class InstructionId implements Serializable {

    /** course */
    private Course course;

    /** instructor */
    private Instructor instructor;
}
