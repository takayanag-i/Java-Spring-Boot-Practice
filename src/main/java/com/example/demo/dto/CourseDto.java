package com.example.demo.dto;

import java.util.List;
import com.example.demo.common.DayOfWeek;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 講座情報の転送用オブジェクト
 */
@Data
@Accessors(chain = true)
public class CourseDto {
    /** 講座ID */
    private String courseId;

    /** 講座名 */
    private String courseName;

    /** 曜日 */
    private DayOfWeek dayOfWeek;

    /** 時限 */
    private String period;

    /** 担当教員名のリスト */
    private List<String> instructorNames;

    /** fromのaction属性の値 */
    private String formAction;

    /** tableセルのrowspan属性の値 */
    private int rowspan = 1;
}
