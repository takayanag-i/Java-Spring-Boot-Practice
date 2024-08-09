package com.example.demo.controller;

import com.example.demo.dto.CourseDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.service.DisplayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final DisplayService displayService;

    @GetMapping("/home")
    public String home(@SessionAttribute("loginStudent") StudentDto loginStudent, Model model) {
        // セッション情報の取得
        String studentId = loginStudent.getStudentId();

        // 表示用時間割データを取得
        List<CourseDto> courses = displayService.getCourses(studentId);
        List<List<CourseDto>> matrix = displayService.getCourseMatrix(courses);

        model.addAttribute("matrix", matrix);
        return "home"; // ビューは後で作成
    }

    @PostMapping("/home")
    public String homePost(@SessionAttribute("loginStudent") StudentDto loginStudent, Model model) {
        // GETメソッドと同じ処理を実行する
        // セッション情報の取得
        String studentId = loginStudent.getStudentId();

        // 表示用時間割データを取得
        List<CourseDto> courses = displayService.getCourses(studentId);
        List<List<CourseDto>> matrix = displayService.getCourseMatrix(courses);

        model.addAttribute("matrix", matrix);
        return "home"; // ビューは後で作成
    }
}
