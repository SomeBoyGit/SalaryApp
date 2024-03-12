package ru.someboy.salaryApplication.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
//import jakarta.persistence.Table;

@Entity
@Table(name = "Employee")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Employee {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String username;

    @Column(name = "password")
    private int password;

    public Employee(String username, int password) {
        this.username = username;
        this.password = password;
    }
}
