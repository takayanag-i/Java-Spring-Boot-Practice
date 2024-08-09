package com.example.demo.service;

import com.example.demo.dto.StudentDto;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.exception.SignUpFailedException;
import com.example.demo.constants.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignUpService {

    private final StudentRepository studentRepository;

    @Transactional
    public void signUp(StudentDto dto) throws SignUpFailedException {
        Student entity = convertDtoToEntity(dto);

        try {
            studentRepository.save(entity);
        } catch (DataAccessException e) {
            if (e.getMessage().contains("Students.PRIMARY")) {
                // 重複する出席番号
                throw new SignUpFailedException(ErrorMessages.DUPLICATE_STUDENT_ID);
            } else if (e.getMessage().contains("Students.email")) {
                // 重複するメールアドレス
                throw new SignUpFailedException(ErrorMessages.DUPLICATE_EMAIL);
            } else {
                throw new RuntimeException(ErrorMessages.DRIVER_SIGNUP_ERROR, e);
            }
        }
    }

    private Student convertDtoToEntity(StudentDto dto) {
        Student entity = new Student();
        entity.setStudentId(dto.getStudentId());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        return entity;
    }
}
