package ru.someboy.salaryApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.someboy.salaryApplication.models.Data;

@Repository
public interface DataRepository extends JpaRepository<Data, Long> {
}
