package com.example.demo.model;

import java.io.Serializable;
import lombok.Data;

/**
 * ID class for the `timetable`.
 */
@Data
public class TimetableId implements Serializable {

    /** course ID */
    private Course course;

    /** day of week number (single-digit numeric string) */
    private String dayOfWeek;

    /** period */
    private String period;
}
