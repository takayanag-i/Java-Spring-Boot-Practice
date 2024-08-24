package com.example.demo.model;

import java.io.Serializable;
import lombok.Data;

/**
 * ID class for the `time`.
 */
@Data
public class TimeId implements Serializable {

    private Course course;
    private String dayOfWeek;
    private String period;
}
