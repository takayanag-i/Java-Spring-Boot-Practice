package com.example.demo.dto;

import java.io.Serializable;
import com.example.demo.common.DayOfWeek;
import lombok.Data;

@Data
public class SearchCriteriaDto implements Serializable {
    private String courseId = "";
    private String courseName = "";
    private String instructorName = "";
    private DayOfWeek dayOfWeek = DayOfWeek.UNSET;
    private String period = "";

    public SearchCriteriaDto() {}

}
