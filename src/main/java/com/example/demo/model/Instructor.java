package com.example.demo.model;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

/**
 * 教員エンティティクラス
 */
@Data
@Entity
public class Instructor {

    /** 教員ID */
    @Id
    @Column(name = "instructor_id", length = 5)
    private String instructorId;

    /** 名前 */
    @Column(name = "name", length = 31)
    private String name;

    /** メールアドレス */
    @Column(name = "email", length = 63)
    private String email;

    /** パスワード */
    @Column(name = "password", length = 63)
    private String password;

    /** 教員に紐づけられた講座担当のリスト */
    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL)
    private List<Instruction> instructions;
}
