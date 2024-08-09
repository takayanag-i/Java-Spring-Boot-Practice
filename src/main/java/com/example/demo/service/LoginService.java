package com.example.demo.service;

import com.example.demo.dto.StudentDto;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.exception.LoginFailedException;
import com.example.demo.constants.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final StudentRepository studentRepository;

    @Transactional(readOnly = true)
    public StudentDto getStudentToLogin(String studentId, String password)
            throws LoginFailedException {
        Student entity = studentRepository.findByStudentIdAndPassword(studentId, password);

        if (entity == null) {
            throw new LoginFailedException(ErrorMessages.LOGIN_FAILED);
        }

        StudentDto dto = new StudentDto();
        dto.setStudentId(entity.getStudentId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setPassword(null); // パスワードは設定しない（セキュリティ上の理由）

        return dto;
    }
}
