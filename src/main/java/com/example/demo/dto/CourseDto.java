package com.example.demo.dto;

import java.util.List;
import com.example.demo.common.DayOfWeek;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CourseDto {
    private String courseId;
    private String courseName;
    private DayOfWeek dayOfWeek;
    private String period;
    private List<String> instructorNames;
    private String formAction;
    private int rowspan = 1;
}
