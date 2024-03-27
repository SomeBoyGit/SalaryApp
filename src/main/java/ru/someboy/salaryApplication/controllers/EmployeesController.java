package ru.someboy.salaryApplication.controllers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.someboy.salaryApplication.dto.EmployeeRequest;
import ru.someboy.salaryApplication.dto.EmployeeResponse;
import ru.someboy.salaryApplication.models.Employee;
import ru.someboy.salaryApplication.services.EmployeesService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeesController {
    private final EmployeesService employeesService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<EmployeeResponse> getEmployees() {
        return employeesService.findAll().stream().map(this::convertToEmployeeResponse).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EmployeeResponse getEmployee(@PathVariable("id") long id) {
        return convertToEmployeeResponse(employeesService.findOne(id));
    }

    private Employee convertToEmployee(EmployeeRequest employeeRequest) {
        return modelMapper.map(employeeRequest, Employee.class);
    }

    private EmployeeResponse convertToEmployeeResponse(Employee employee) {
        return modelMapper.map(employee, EmployeeResponse.class);
    }
}
