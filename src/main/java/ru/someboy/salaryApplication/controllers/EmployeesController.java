package ru.someboy.salaryApplication.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.someboy.salaryApplication.models.Employee;
import ru.someboy.salaryApplication.services.EmployeesService;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeesController {
    private final EmployeesService employeesService;

    @GetMapping
    public List<Employee> getEmployees() {
        return employeesService.findAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable("id") long id) {
        return employeesService.findOne(id);
    }
}
