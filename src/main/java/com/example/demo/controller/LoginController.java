package com.example.demo.controller;

import com.example.demo.dto.StudentDto;
import com.example.demo.exception.InvalidInputException;
import com.example.demo.exception.LoginFailedException;
import com.example.demo.service.LoginService;
import com.example.demo.util.CheckUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

/**
 * ログイン機能のコントローラクラス
 */
@Controller
@RequiredArgsConstructor
public class LoginController {

    /** サービスクラス */
    private final LoginService loginService;

    /**
     * GETリクエストに対する処理：ログイン画面に遷移する
     * 
     * @param model ビューに渡すデータを保持するモデル
     * @return login.html テンプレート
     */
    @GetMapping("/login")
    public String getLoginForm(Model model) {
        model.addAttribute("studentDto", new StudentDto()); // TODO これはなぜ必要
        return "login";
    }

    /**
     * 出席番号とパスワードでログインする
     * 
     * @param studentId
     * @param password
     * @param session
     * @param model
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestParam("studentId") String studentId,
            @RequestParam("password") String password,
            HttpSession session,
            Model model) {

        // バリデーションチェック
        try {
            CheckUtil.checkStudentId(studentId);
            CheckUtil.checkPassword(password);
        } catch (InvalidInputException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "login";
        }

        try {
            // ログインユーザの情報を取得
            StudentDto loginStudent = loginService.getStudentToLogin(studentId, password);
            session.setAttribute("loginStudent", loginStudent);
        } catch (LoginFailedException e) {
            // ログイン失敗時
            // エラーメッセージを渡してログイン画面に再フォワード
            model.addAttribute("errorMessage", e.getMessage());
            return "login";
        }

        // ホーム画面にリダイレクト
        return "redirect:/home";
    }

}
