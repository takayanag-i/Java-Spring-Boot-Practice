package com.example.demo.model;

import java.io.Serializable;
import lombok.Data;

/**
 * ID class for the `instruction`.
 */
@Data
public class InstructionId implements Serializable {

    private Course course;
    private Instructor instructor;
}
