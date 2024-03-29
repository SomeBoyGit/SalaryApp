package ru.someboy.salaryApplication.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ShopRequest {
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
}
