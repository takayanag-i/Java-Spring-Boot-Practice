package com.example.demo.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class InstructionId implements Serializable {
    private String courseId;
    private String instructorId;
}
