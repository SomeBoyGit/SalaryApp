package ru.someboy.salaryApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.someboy.salaryApplication.models.Authority;

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authority, Integer> {
}
