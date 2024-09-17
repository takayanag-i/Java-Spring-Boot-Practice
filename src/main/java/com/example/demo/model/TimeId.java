package com.example.demo.model;

import java.io.Serializable;
import lombok.Data;

/**
 * コマエンティティのIDクラス
 */
@Data
public class TimeId implements Serializable {

    private Course course;
    private String dayOfWeek;
    private String period;
}
