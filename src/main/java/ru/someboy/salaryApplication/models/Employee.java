package ru.someboy.salaryApplication.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
//@ToString
public class Employee {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    @Size(min = 2, max = 50, message = "ФИО должно содержать от 2 до 50 символов")
    private String username;

    @Column(name = "password")
    private int password;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "date_of_registration")
    private LocalDate dateOfRegistration;

    @Column(name = "is_work")
    private Boolean isWork;

    @ManyToMany(mappedBy = "employees")
    private List<Authority> authorities;

    @ManyToMany(mappedBy = "employees")
    private List<Shop> shops;

    @OneToMany(mappedBy = "employee")
    private List<Repair> repairs;

    public Employee(String username, int password, LocalDate dateOfBirth) {
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password=" + password +
                ", dateOfBirth=" + dateOfBirth +
                ", dateOfRegistration=" + dateOfRegistration +
                ", isWork=" + isWork +
                ", authorities=" + authorities +
                ", shops=" + shops.size() +
                ", repairs=" + repairs.size() +
                '}';
    }
}
