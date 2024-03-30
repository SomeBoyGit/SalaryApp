package ru.someboy.salaryApplication.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.someboy.salaryApplication.models.Employee;
import ru.someboy.salaryApplication.models.Repair;
import ru.someboy.salaryApplication.repositories.RepairsRepository;

import java.time.LocalDateTime;
import java.util.List;

import static ru.someboy.salaryApplication.util.Methods.getPresentOptional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RepairsService {
    private final RepairsRepository repairsRepository;
    private final EmployeesService employeesService;

    public List<Repair> findAll() {
        return repairsRepository.findAll();
    }

    public Repair findOne(long id) {
        return repairsRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Repair repair) {
        repairsRepository.save(enrichNew(repair));
    }

    @Transactional
    public void updateDoneRepair(Long id) {
        Repair updateRepair = (Repair) getPresentOptional(repairsRepository.findById(id));
        updateRepair.setIsDone(true);
        updateRepair.setDateOfDone(LocalDateTime.now());
        repairsRepository.save(updateRepair);
    }

    @Transactional
    public void updateCancelDoneRepair(long id) {
        Repair updateRepair = (Repair) getPresentOptional(repairsRepository.findById(id));
        updateRepair.setIsDone(false);
        updateRepair.setDateOfDone(null);
        repairsRepository.save(updateRepair);
    }

    @Transactional
    public void updateIssueRepair(Repair updateRepair, Long id) {
        repairsRepository.save(enrichUpdate(updateRepair, id));
    }

    @Transactional
    public void cancelledIssue(Long id) {
        repairsRepository.save(enrichCancelledIssueRepair(id));
    }

    @Transactional
    public void editRepair(Repair updateRepair, Long id) {
        repairsRepository.save(enrichEdit(updateRepair, id));
    }

    @Transactional
    public void delete(Long id) {
        repairsRepository.deleteById(id);
    }

    private Repair enrichNew(Repair newRepair) {
        Employee seller = employeesService.findOne(1);// TODO времянка
        newRepair.setDateOfAcceptance(LocalDateTime.now());
        newRepair.setAnnotation("Приём ремонта:\n" + newRepair.getAnnotation());
//            repair.setSeller(getCurrentEmployee);
        newRepair.setSeller(seller);
        newRepair.setIsDone(false);
        return newRepair;
    }

    private Repair enrichEdit(Repair repair, Long id) {
        Repair editRepair = (Repair) getPresentOptional(repairsRepository.findById(id));
//        Repair editRepair = repairsRepository.findById(id).get();
        if (repair.getPhoneNumber() != null) editRepair.setPhoneNumber(repair.getPhoneNumber());
        if (repair.getRepairsRevenue() != null) editRepair.setRepairsRevenue(repair.getRepairsRevenue());
        if (repair.getRepairsPurchase() != null) editRepair.setRepairsPurchase(repair.getRepairsPurchase());
        if (repair.getMaster() != null) editRepair.setMaster(repair.getMaster());
        return editRepair;
    }

    private Repair enrichUpdate(Repair repair, Long id) {
        Repair updateRepair = (Repair) getPresentOptional(repairsRepository.findById(id));
        if (updateRepair.getIsDone()) {
            int sellerPercent = 10;// TODO времянка
            int masterPercent = 45;// TODO времянка
            updateRepair.setRepairsRevenue(repair.getRepairsRevenue());
            updateRepair.setRepairsPurchase(repair.getRepairsPurchase());
            updateRepair.setDateOfIssue(LocalDateTime.now());
            updateRepair.setAnnotation(updateRepair.getAnnotation() + "\nВыдача ремонта:\n" + repair.getAnnotation());
//            repair.setEmployee(getCurrentEmployee);
            updateRepair.setEmployee(employeesService.findOne(2));// TODO времянка
            updateRepair.setMaster(repair.getMaster());
            updateRepair.setRepairsProfit((double) (repair.getRepairsRevenue() - repair.getRepairsPurchase()));
//            TODO добавить метод расчета sellerSalary,masterSalary.
            updateRepair.setSellerSalary(updateRepair.getRepairsProfit() * sellerPercent / 100);// TODO времянка
            updateRepair.setMasterSalary(updateRepair.getRepairsProfit() * masterPercent / 100);// TODO времянка
        }
        return updateRepair;
    }

    /*TODO
     * Если продавец, выдающий и мастер -- один человек
     * Если продавец и выдающий - один, мастер - другой
     * Если продавец и мастер - один, выдающий - другой
     * Если продавец один, выдающий и мастер - другой
     * Если продавец, выдающий и мастер - разные люди*/
    private void salaryCalculation() {

    }

    private Repair enrichCancelledIssueRepair(Long id) {
        Repair repair = (Repair) getPresentOptional(repairsRepository.findById(id));
        if (repair.getIsDone()) {
            repair.setRepairsRevenue(null);
            repair.setRepairsPurchase(null);
            repair.setDateOfIssue(null);
            repair.setAnnotation(repair.getAnnotation().substring(0, repair.getAnnotation().indexOf("\nВыдача ремонта:")));
//            repair.setEmployee(getCurrentEmployee);
            repair.setEmployee(null);
            repair.setMaster(null);
            repair.setRepairsProfit(null);
            repair.setSellerSalary(null);
            repair.setMasterSalary(null);
        }
        return repair;
    }
}