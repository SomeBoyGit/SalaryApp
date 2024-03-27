package ru.someboy.salaryApplication.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.someboy.salaryApplication.models.DataFullMonth;
import ru.someboy.salaryApplication.repositories.DataFullMonthRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DataFullMonthService {
    private final DataFullMonthRepository dfmRepository;

    public List<DataFullMonth> findAll() {
        return dfmRepository.findAll();
    }

    public DataFullMonth findOne(long id) {
        return dfmRepository.findById(id).orElse(null);
    }
}
