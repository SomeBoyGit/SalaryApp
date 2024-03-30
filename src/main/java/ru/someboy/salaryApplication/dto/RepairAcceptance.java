package ru.someboy.salaryApplication.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

@Data
public class RepairAcceptance {
    @NotNull
    @Size(min = 10, max = 15, message = "Номер телефона должен содержать от 10 до 15 символов")
    private String phoneNumber;

    @NotNull
    @Size(max = 300, message = "Аннотация должна содержать не более 300 символов")
    private String annotation;

    @NotNull
    private Integer shopId;

}
