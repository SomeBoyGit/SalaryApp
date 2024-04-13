package ru.someboy.salaryApplication.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ShopRequest {
    @NotNull
    @Size(min = 3, max = 30, message = "Название должно содержать от 3 до 30 символов")
    private String name;

    @NotNull
    private Integer rental;

    @NotNull
    private Integer accessoriesPercent;

    @NotNull
    private Integer bonusDepends;

    @NotNull
    private Integer bonusAmount;

    @NotNull
    private Integer marketingMarginPercent;

    @NotNull
    private Integer simPercent;

    @NotNull
    private Integer devicesPercent;

    @NotNull
    private Integer minimumWage;

    @NotNull
    private Boolean isWork;
}
