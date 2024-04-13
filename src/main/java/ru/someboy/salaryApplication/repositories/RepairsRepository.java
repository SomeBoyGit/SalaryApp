package ru.someboy.salaryApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.someboy.salaryApplication.models.Repair;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RepairsRepository extends JpaRepository<Repair, Long> {
    @Query("select r from Repair r where (r.dateOfIssue between ?1 and ?2) " +
            "and (r.employee.id=?3 or r.seller.id=?3 or r.master.id=?3)")
    List<Repair> findAllRepairByDateOfIssueBetweenAndEmployee(LocalDateTime start, LocalDateTime end, Long employeeId);
}
