package ru.someboy.salaryApplication.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.someboy.salaryApplication.models.Data;
import ru.someboy.salaryApplication.repositories.DataRepository;
import ru.someboy.salaryApplication.util.Methods;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DataService {
    private final DataRepository dataRepository;

    public List<Data> findAll() {
        return dataRepository.findAll();
    }

    public Data findOne(long id) {
        return dataRepository.findById(id).orElse(null);
    }

    public void save(Data data) {
        dataRepository.save(data);
    }

    private void enrichData(Data data, String dateIn) {
        data.setDateOfData(
                dateIn == null ? LocalDateTime.now()
                        : Methods.stringDateTimeToLocalDateTimePlusMin(dateIn)
        );
        data.setDateOfEntry(LocalDateTime.now());
//        measurement.setSensor(sensorRepository.findByName(measurement.getSensor().getName()).get(0));
    }
}
