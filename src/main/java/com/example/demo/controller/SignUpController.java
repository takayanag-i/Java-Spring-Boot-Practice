package com.example.demo.controller;

import com.example.demo.dto.StudentDto;
import com.example.demo.exception.SignUpFailedException;
import com.example.demo.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
@RequiredArgsConstructor
public class SignUpController {

    private final SignUpService signUpService;

    @GetMapping("/signup")
    public String getSignUpForm(Model model) {
        model.addAttribute("studentDto", new StudentDto());
        return "signup"; // ビューは後で作成
    }

    @PostMapping("/signup")
    public String signUp(@Valid @ModelAttribute StudentDto studentDto, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage",
                    bindingResult.getAllErrors().get(0).getDefaultMessage());
            return "signup"; // ビューは後で作成
        }

        try {
            // 新しい学生を登録
            signUpService.signUp(studentDto);
        } catch (SignUpFailedException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "signup"; // ビューは後で作成
        }

        return "redirect:/login"; // ビューは後で作成
    }
}
