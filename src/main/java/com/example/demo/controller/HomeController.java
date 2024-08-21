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
        String studentId = loginStudent.getStudentId();
        List<List<CourseDto>> matrix = displayService.getCourseMatrix(studentId);

        model.addAttribute("matrix", matrix);
        return "home";
    }

    @PostMapping("/home")
    public String homePost(@SessionAttribute("loginStudent") StudentDto loginStudent, Model model) {
        String studentId = loginStudent.getStudentId();
        List<List<CourseDto>> matrix = displayService.getCourseMatrix(studentId);

        model.addAttribute("matrix", matrix);
        return "home";
    }
}
