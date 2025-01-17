package com.example.demo.model;

import java.io.Serializable;
import lombok.Data;

/**
 * 履修登録エンティティのIDクラス
 */
@Data
public class EnrollmentId implements Serializable {

    private String studentId;
    private Course course;
}
