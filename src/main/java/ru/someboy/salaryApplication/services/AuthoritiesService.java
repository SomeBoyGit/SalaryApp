package ru.someboy.salaryApplication.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.someboy.salaryApplication.models.Authority;
import ru.someboy.salaryApplication.models.Employee;
import ru.someboy.salaryApplication.repositories.AuthoritiesRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthoritiesService {
    private final AuthoritiesRepository authoritiesRepository;
    public List<Authority> findAll() {
        return authoritiesRepository.findAll();
    }

    public Authority findOne(int id) {
        return authoritiesRepository.findById(id).orElse(null);
    }
}
