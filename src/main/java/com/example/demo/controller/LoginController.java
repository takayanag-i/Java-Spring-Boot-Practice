package com.example.demo.controller;

import com.example.demo.dto.StudentDto;
import com.example.demo.exception.LoginFailedException;
import com.example.demo.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String getLoginForm(Model model) {
        model.addAttribute("studentDto", new StudentDto());
        return "login"; // ビューは後で作成
    }

    @PostMapping("/login")
    public String login(@RequestParam("studentId") String studentId,
            @RequestParam("password") String password,
            HttpSession session,
            Model model) {
        // バリデーションチェック
        if (studentId == null || studentId.isEmpty()) {
            model.addAttribute("errorMessage", "出席番号は必須です。");
            return "login"; // ビューは後で作成
        }
        if (password == null || password.isEmpty()) {
            model.addAttribute("errorMessage", "パスワードは必須です。");
            return "login"; // ビューは後で作成
        }

        try {
            // ログインユーザの情報を取得
            StudentDto loginStudent = loginService.getStudentToLogin(studentId, password);
            session.setAttribute("loginStudent", loginStudent);
        } catch (LoginFailedException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "login"; // ビューは後で作成
        }

        return "redirect:/home"; // ビューは後で作成
    }

}
