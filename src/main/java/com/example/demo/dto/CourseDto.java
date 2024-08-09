package com.example.demo.dto;

import java.util.List;
import com.example.demo.common.DayOfWeek;
import lombok.Data;

@Data
public class CourseDto {
    private String courseId;
    private String courseName;
    private DayOfWeek dayOfWeek;
    private String period;
    private List<String> instructors;
    private String formAction;
    private int rowspan = 1;
}
