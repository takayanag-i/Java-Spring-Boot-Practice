package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * 生徒エンティティクラス
 */
@Data
@Entity
public class Student {

    /** 出席番号 */
    @Id
    @Column(name = "student_id", length = 4)
    private String studentId;

    /** 名前 */
    @Column(name = "name", length = 31)
    private String name;

    /** メールアドレス */
    @Column(name = "email", length = 63)
    private String email;

    /** パスワード */
    @Column(name = "password", length = 63)
    private String password;
}
