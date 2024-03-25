package ru.someboy.salaryApplication.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "shop")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Shop {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "name")
    @Size(min = 3, max = 30, message = "Name should be between 3 and 30 characters")
    private String name;

    @NotNull
    @Column(name = "rental")
    private Integer rental;

    @NotNull
    @Column(name = "accessories_percent")
    private Integer accessoriesPercent;

    @NotNull
    @Column(name = "is_work")
    private Boolean isWork;


}
