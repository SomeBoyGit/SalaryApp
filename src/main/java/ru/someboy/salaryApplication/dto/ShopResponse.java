package ru.someboy.salaryApplication.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ru.someboy.salaryApplication.models.Employee;

import java.time.LocalDate;
import java.util.List;

@Data
public class ShopResponse {
    @NotNull
    private Integer id;

    @NotNull
    @Size(min = 3, max = 30, message = "Название должно содержать от 3 до 30 символов")
    private String name;

    @NotNull
    private Double rental;

    @NotNull
    private Integer accessoriesPercent;

    @NotNull
    private Integer marketingMarginPercent;

    @NotNull
    private Integer simPercent;

    @NotNull
    private Integer devicesPercent;

    @NotNull
    private Boolean isWork;

    @NotNull
    private LocalDate dateOfRegistration;

    private List<Employee> employees;
}
