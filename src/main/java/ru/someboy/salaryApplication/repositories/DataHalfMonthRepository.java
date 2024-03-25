package ru.someboy.salaryApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.someboy.salaryApplication.models.DataHalfMonth;

@Repository
public interface DataHalfMonthRepository extends JpaRepository<DataHalfMonth, Long> {
}
