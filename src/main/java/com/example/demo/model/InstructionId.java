package com.example.demo.model;

import java.io.Serializable;
import lombok.Data;

/**
 * 講座担当エンティティのIDクラス
 */
@Data
public class InstructionId implements Serializable {

    private Course course;
    private Instructor instructor;
}
