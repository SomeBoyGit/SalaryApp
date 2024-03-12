package ru.someboy.salaryApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.someboy.salaryApplication.models.Employee;

@Repository
public interface EmployeesRepository extends JpaRepository<Employee, Long> {
}
