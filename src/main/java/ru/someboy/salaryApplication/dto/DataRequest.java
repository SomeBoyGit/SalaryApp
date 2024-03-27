package ru.someboy.salaryApplication.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DataRequest {
    private Integer accessoriesCount;

    private Integer accessoriesRevenue;

    private Integer marketingMargin;

    private Integer devicesCount;

    private Integer devicesRevenue;

    private Integer devicesPurchase;

    private LocalDateTime dateOfData;

    private Integer expenses;

    @Size(max = 300, message = "Аннотация не должна содержать более 300 символов")
    private String annotation;

    private Integer cash;

    private Integer cashless;
}
