package ru.someboy.salaryApplication.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import ru.someboy.salaryApplication.models.Employee;
import ru.someboy.salaryApplication.models.Repair;
import ru.someboy.salaryApplication.models.Shop;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class DataResponse {
    @NotNull
    private Long id;

    @NotNull
    private Integer accessoriesCount;

    @NotNull
    private Integer accessoriesRevenue;

    @NotNull
    private Double accessoriesSalary;

    @NotNull
    private Integer bonus;

    @NotNull
    private Integer marketingMargin;

    @NotNull
    private Double marketingMarginProfit;

    @NotNull
    private Double marketingMarginSalary;

    @NotNull
    private Integer simCount;

    @NotNull
    private Integer simRevenue;

    @NotNull
    private Double simSalary;

    @NotNull
    private Integer devicesCount;

    @NotNull
    private Integer devicesRevenue;

    @NotNull
    private Integer devicesPurchase;

    @NotNull
    private Integer devicesProfit;

    @NotNull
    private Double devicesSalary;

    @NotNull
    private Integer repairsCount;

    @NotNull
    private Integer repairsRevenue;

    @NotNull
    private Integer repairsPurchase;

    @NotNull
    private Integer repairsProfit;

    @NotNull
    private Double repairsSalary;

    @NotNull
    private LocalDateTime dateOfEntry;

    @NotNull
    private LocalDateTime dateOfData;

    @NotNull
    private Integer expenses;

    @NotNull
    @Size(max = 300, message = "Аннотация не должна содержать более 300 символов")
    private String annotation;

    @NotNull
    private Integer cash;

    @NotNull
    private Integer cashless;

    @NotNull
    private Double salary;

    @NotNull
    private Employee employee;

    @NotNull
    private Shop shop;

    @NotNull
    private List<Repair> repairs;
}
