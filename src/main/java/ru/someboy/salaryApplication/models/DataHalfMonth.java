package ru.someboy.salaryApplication.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "data_half_month")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class DataHalfMonth {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;
}
