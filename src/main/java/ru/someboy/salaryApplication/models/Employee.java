package ru.someboy.salaryApplication.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
//import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Employee {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String username;

    @Column(name = "password")
    private int password;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "date_of_registration")
    private LocalDate dateOfRegistration;

    @Column(name = "is_work")
    private Boolean isWork;

    @OneToMany(mappedBy = "employee")
    private List<Authority> authorities;

    public Employee(String username, int password, LocalDate dateOfBirth) {
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }
}
