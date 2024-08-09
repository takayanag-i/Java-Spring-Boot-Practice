package com.example.demo.controller;

import com.example.demo.dto.CourseDto;
import com.example.demo.dto.EnrollmentDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.exception.InvalidEnrollmentException;
import com.example.demo.service.DisplayService;
import com.example.demo.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.sql.Timestamp;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;
    private final DisplayService displayService;

    @PostMapping("/enrollment")
    public String enroll(@Valid @ModelAttribute EnrollmentDto enrollmentDto,
            BindingResult bindingResult,
            @SessionAttribute("loginStudent") StudentDto loginStudent, Model model,
            HttpSession session) {

        // パラメータの取得
        String courseId = enrollmentDto.getCourseId();
        String studentId = loginStudent.getStudentId();

        // バリデーションチェック
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage",
                    bindingResult.getAllErrors().get(0).getDefaultMessage());
            return "search"; // ビューは後で作成
        }

        // 現在日時の取得
        enrollmentDto.setEnrollmentDate(new Timestamp(System.currentTimeMillis()));

        // 履修登録の実行
        try {
            enrollmentDto.setStudentId(studentId);
            enrollmentDto.setCourseId(courseId);
            enrollmentService.enroll(enrollmentDto);

            // 表示用時間割データを取得
            List<CourseDto> courses = displayService.getCourses(studentId);
            List<List<CourseDto>> matrix = displayService.getCourseMatrix(courses);
            model.addAttribute("matrix", matrix);

            return "home"; // ビューは後で作成

        } catch (InvalidEnrollmentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "search"; // ビューは後で作成
        }
    }
}
