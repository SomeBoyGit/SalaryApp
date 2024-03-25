package ru.someboy.salaryApplication.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "authority")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Authority {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @Column(name = "value")
    @Size(min = 2, max = 30, message = "Value should be between 2 and 30 characters")
    private String value;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "employee", referencedColumnName = "id")
    private Employee employee;
}
