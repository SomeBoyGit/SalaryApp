package ru.someboy.salaryApplication.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RepairIssue {
    @NotNull
    private Integer repairsRevenue;

    @NotNull
    private Integer repairsPurchase;

    @NotNull
    @Size(max = 300, message = "Аннотация должна содержать не более 300 символов")
    private String annotation;

    @NotNull
    private Integer employeeIndex;

    @NotNull
    private Integer masterIndex;

}
