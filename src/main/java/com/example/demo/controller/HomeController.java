package com.example.demo.controller;

import com.example.demo.dto.CourseDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.service.DisplayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

/**
 * ホーム画面表示機能のコントローラ
 */
@Controller
@RequiredArgsConstructor
public class HomeController {

    /** サービスクラス */
    private final DisplayService displayService;

    /**
     * GETリクエストに対する処理：ログイン画面に遷移する
     * 
     * @param loginStudent ログイン中の生徒 {@link StudentDto} オブジェクト
     * @param model ビューに渡すデータを保持するモデル
     * @return home.html テンプレート
     */
    @GetMapping("/home")
    public String home(@SessionAttribute("loginStudent") StudentDto loginStudent, Model model) {
        // セッションからログイン中の生徒の出席番号を取得
        String studentId = loginStudent.getStudentId();

        // 出席番号から時間割マトリクスを取得
        List<List<CourseDto>> matrix = displayService.getCourseMatrix(studentId);
        model.addAttribute("matrix", matrix);

        // ホーム画面を表示
        return "home";
    }
}
