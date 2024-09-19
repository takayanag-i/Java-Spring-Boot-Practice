package com.example.demo.dto;

import java.io.Serializable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 講座の検索条件オブジェクト
 */
@Data
public class SearchCriteriaDto implements Serializable {

    /** 講座ID */
    @NotNull
    @Size(max = 5, message = "講座IDは5文字以下です")
    private String courseId = "";

    /** 講座名 */
    @NotNull
    @Size(max = 63, message = "講座名は63文字以下です")
    private String courseName = "";

    /** 教員名 */
    @NotNull
    @Size(max = 31, message = "教員名は31文字以下です")
    private String instructorName = "";

    /** 曜日番号 {@link String} */
    private String dayOfWeek = "";

    /** 時限 */
    @NotNull
    @Size(max = 1, message = "時限は1桁です")
    private String period = "";

    public SearchCriteriaDto() {}

}
