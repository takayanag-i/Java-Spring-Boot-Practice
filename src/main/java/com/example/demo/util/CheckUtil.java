package com.example.demo.util;

import com.example.demo.constants.ErrorMessages;
import com.example.demo.exception.InvalidInputException;

/**
 * バリデーションチェック用のユーティリティクラス
 */
public class CheckUtil {
    /**
     * 出席番号のバリデーションチェック
     * 
     * @param studentId 出席番号
     * @throws InvalidInputException 不正な入力があったときにスローされる例外
     */
    public static void checkStudentId(String studentId) throws InvalidInputException {
        if (studentId == null || "".equals(studentId)) {
            throw new InvalidInputException(ErrorMessages.REQUIRED_STUDENT_ID);
        }
        if (!studentId.matches("\\d{4}")) {
            throw new InvalidInputException(ErrorMessages.FORMAT_STUDENT_ID);
        }
    }

    /**
     * パスワードのバリデーションチェック
     * 
     * @param password パスワード
     * @throws InvalidInputException 不正な入力があったときにスローされる例外
     */
    public static void checkPassword(String password) throws InvalidInputException {
        if (password == null || "".equals(password)) {
            throw new InvalidInputException("パスワードを入力してください。");
        }
        if (password.length() > 63 || !password.matches("[\\w!@#$%^&*()]+")) {
            throw new InvalidInputException("パスワードは63文字以内の半角英数および記号(!@#$%^&*())で入力してください。");
        }
    }

    // TODO プライベートコンストラクタ
}
