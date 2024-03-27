package ru.someboy.salaryApplication.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import ru.someboy.salaryApplication.models.Authority;
import ru.someboy.salaryApplication.models.Repair;
import ru.someboy.salaryApplication.models.Shop;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class EmployeeResponse {
    @NotNull
    private Long id;

    @NotNull
    @Size(min = 2, max = 50, message = "ФИО должно содержать от 2 до 50 символов")
    private String username;

    @NotNull
    private int password;

    @NotNull
    private LocalDate dateOfBirth;

    @NotNull
    private LocalDate dateOfRegistration;

    @NotNull
    private Boolean isWork;

//    private List<Authority> authorities;
//
//    private List<Shop> shops;
//
//    private List<Repair> repairs;
}
