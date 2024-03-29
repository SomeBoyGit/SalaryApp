package ru.someboy.salaryApplication.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.someboy.salaryApplication.models.Repair;
import ru.someboy.salaryApplication.repositories.RepairsRepository;

import java.util.List;

import static ru.someboy.salaryApplication.util.Methods.getPresentOptional;

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

    @Transactional
    public void save(Repair repair) {
        repairsRepository.save(enrichRepair(repair, false, null));
    }

    @Transactional
    public void update(Repair updateRepair, Long id) {
        repairsRepository.save(enrichRepair(updateRepair, true, id));
    }

    @Transactional
    public void delete(Long id) {
        repairsRepository.deleteById(id);
    }

    private Repair enrichRepair(Repair repair, boolean isUpdate, Long id) {
        if (isUpdate) {
            Repair updateRepair = (Repair) getPresentOptional(repairsRepository.findById(id));
            if(repair.getPhoneNumber() != null) updateRepair.setPhoneNumber(repair.getPhoneNumber());
            return updateRepair;
        } else {

//            shop.setDateOfRegistration(LocalDate.now());
            return repair;
        }
    }
}