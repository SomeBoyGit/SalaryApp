package ru.someboy.salaryApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.someboy.salaryApplication.models.Repair;

@Repository
public interface RepairsRepository extends JpaRepository<Repair, Long> {
}
