package ru.someboy.salaryApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.someboy.salaryApplication.models.Shop;

@Repository
public interface ShopsRepository extends JpaRepository<Shop, Integer> {
}
