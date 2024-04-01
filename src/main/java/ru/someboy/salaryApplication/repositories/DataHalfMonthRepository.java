package ru.someboy.salaryApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.someboy.salaryApplication.models.DataHalfMonth;
import ru.someboy.salaryApplication.models.Employee;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface DataHalfMonthRepository extends JpaRepository<DataHalfMonth, Long> {
    Optional<DataHalfMonth> findDataHalfMonthByDateAndEmployee(LocalDateTime date, Employee employee);
}
