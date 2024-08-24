package com.example.demo.dto;

import java.util.List;
import com.example.demo.common.DayOfWeek;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Data Transfer Object for Course information.
 */
@Data
@Accessors(chain = true)
public class CourseDto {
    /** The ID of the course. */
    private String courseId;

    /** The name of the course. */
    private String courseName;

    /** The day of the week. */
    private DayOfWeek dayOfWeek;

    /** The period. */
    private String period;

    /** The list of instructor names for the course. */
    private List<String> instructorNames;

    /** The action property value for form submission. */
    private String formAction;

    /** The value of the rowspan property for table display. */
    private int rowspan = 1;
}
