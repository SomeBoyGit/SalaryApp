package ru.someboy.salaryApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.someboy.salaryApplication.models.Data;
import ru.someboy.salaryApplication.models.Employee;
import ru.someboy.salaryApplication.models.Shop;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface DataRepository extends JpaRepository<Data, Long> {
    Optional<Data> findDataByDateOfDataAndEmployee(LocalDateTime dateOfData, Employee employee);

    List<Data> findAllDataByDateOfDataBetweenAndEmployee(LocalDateTime start, LocalDateTime end, Employee employee);

    List<Data> findDataByDateOfDataAndShop(LocalDateTime dateOfData, Shop shop);
}
