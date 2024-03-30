package ru.someboy.salaryApplication.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.someboy.salaryApplication.models.Employee;
import ru.someboy.salaryApplication.models.Shop;

import java.time.LocalDateTime;

@Data
public class RepairResponse {
    @NotNull
    private Long id;

    @NotNull
    private String phoneNumber;

    private Integer repairsRevenue;

    private Integer repairsPurchase;

    private Double repairsProfit;

    private Double repairsSalary;

    private LocalDateTime dateOfAcceptance;

    private LocalDateTime dateOfDone;

    private LocalDateTime dateOfIssue;

    private Boolean isDone;

    private String annotation;

    private Employee employee;

    private Employee seller;

    private Employee master;

    private Shop shop;
}
