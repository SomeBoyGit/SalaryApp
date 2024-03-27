package ru.someboy.salaryApplication.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.someboy.salaryApplication.models.DataHalfMonth;
import ru.someboy.salaryApplication.repositories.DataHalfMonthRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DataHalfMonthService {
    private final DataHalfMonthRepository dhmRepository;

    public List<DataHalfMonth> findAll() {
        return dhmRepository.findAll();
    }

    public DataHalfMonth findOne(long id) {
        return dhmRepository.findById(id).orElse(null);
    }
}
