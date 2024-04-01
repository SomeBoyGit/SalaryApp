package ru.someboy.salaryApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.someboy.salaryApplication.models.DataFullMonth;
import ru.someboy.salaryApplication.models.Employee;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface DataFullMonthRepository extends JpaRepository<DataFullMonth, Long> {
//    @Query("select u from Data u where u.emailAddress = ?1")
//    DataSum findByEmailAddress(String emailAddress);
    Optional<DataFullMonth> findDataFullMonthByDateAndEmployee(LocalDateTime date, Employee employee);
}
