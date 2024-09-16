package com.example.demo.util;

import com.example.demo.exception.InvalidInputException;

public class CheckUtil {
    /**
     * 出席番号のバリデーションチェック
     * 
     * @param studentId 出席番号
     * @throws InvalidInputException 不正な入力があったときにスローされる例外
     */
    public static void checkStudentId(String studentId) throws InvalidInputException {
        if (studentId == null || "".equals(studentId)) {
            throw new InvalidInputException("IDを入力してください。");
        }
        if (!studentId.matches("\\d{4}")) {
            throw new InvalidInputException("IDは4桁の半角数字で入力してください。");
        }
    }
}
