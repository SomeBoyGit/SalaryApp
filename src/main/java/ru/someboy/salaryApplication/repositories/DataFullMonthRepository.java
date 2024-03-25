package ru.someboy.salaryApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.someboy.salaryApplication.models.DataFullMonth;

@Repository
public interface DataFullMonthRepository extends JpaRepository<DataFullMonth, Long> {
}
