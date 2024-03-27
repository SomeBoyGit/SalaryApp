package ru.someboy.salaryApplication.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.someboy.salaryApplication.models.Repair;
import ru.someboy.salaryApplication.repositories.RepairsRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RepairsService {
    private final RepairsRepository repairsRepository;

    public List<Repair> findAll() {
        return repairsRepository.findAll();
    }

    public Repair findOne(long id) {
        return repairsRepository.findById(id).orElse(null);
    }
}