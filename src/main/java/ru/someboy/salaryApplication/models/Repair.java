package ru.someboy.salaryApplication.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "repair")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Repair {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "employee", referencedColumnName = "id")
    private Employee employee;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "seller", referencedColumnName = "id")
    private Employee seller;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "master", referencedColumnName = "id")
    private Employee master;
}
