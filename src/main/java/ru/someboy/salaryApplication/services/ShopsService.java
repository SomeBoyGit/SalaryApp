package ru.someboy.salaryApplication.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.someboy.salaryApplication.models.Shop;
import ru.someboy.salaryApplication.repositories.ShopsRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ShopsService {
    private final ShopsRepository shopsRepository;

    public List<Shop> findAll() {
        return shopsRepository.findAll();
    }

    public Shop findOne(int id) {
        return shopsRepository.findById(id).orElse(null);
    }
}
