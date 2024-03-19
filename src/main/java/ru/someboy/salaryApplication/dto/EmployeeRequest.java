package ru.someboy.salaryApplication.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class EmployeeRequest {

    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String username;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
    private int password;

    private LocalDate dateOfBirth;
}
