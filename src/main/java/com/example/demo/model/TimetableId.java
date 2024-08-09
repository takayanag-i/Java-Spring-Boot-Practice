package com.example.demo.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class TimetableId implements Serializable {
    private String courseId;
    private String dayOfWeek;
    private String period;
}
