package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StudentDto {

    @NotEmpty(message = "Student ID is required")
    @Size(max = 4, message = "Student ID must be at most 4 characters long")
    private String studentId;

    @NotEmpty(message = "Name is required")
    @Size(max = 31, message = "Name must be at most 31 characters long")
    private String name;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email should be valid")
    @Size(max = 63, message = "Email must be at most 63 characters long")
    private String email;

    @NotEmpty(message = "Password is required")
    @Size(max = 63, message = "Password must be at most 63 characters long")
    private String password;
}
