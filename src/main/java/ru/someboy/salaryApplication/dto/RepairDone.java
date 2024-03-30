package ru.someboy.salaryApplication.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RepairDone {
    @NotNull
    private Boolean isDone;
}
