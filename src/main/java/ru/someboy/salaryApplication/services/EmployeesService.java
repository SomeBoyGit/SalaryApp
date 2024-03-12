package ru.someboy.salaryApplication.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.someboy.salaryApplication.models.Employee;
import ru.someboy.salaryApplication.repositories.EmployeesRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EmployeesService {
    private final EmployeesRepository employeesRepository;

    public List<Employee> findAll() {
        return employeesRepository.findAll();
    }

    public Employee findOne(long id) {
        return employeesRepository.findById(id).orElse(null);
    }
}
