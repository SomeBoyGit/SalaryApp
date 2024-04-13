package ru.someboy.salaryApplication.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.someboy.salaryApplication.models.Employee;
import ru.someboy.salaryApplication.models.Repair;
import ru.someboy.salaryApplication.repositories.RepairsRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static ru.someboy.salaryApplication.util.Methods.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RepairsService2 {
    private final RepairsRepository repairsRepository;
    private final DataService2 dataService;
//    private final DataService dataService;

    public List<Repair> findAll() {
        return repairsRepository.findAll();
    }

    public Repair findOne(long id) {
        return repairsRepository.findById(id).orElse(null);
    }

    public List<Repair> findAllRepairByDateOfIssueBetweenAndEmployee(LocalDateTime start, LocalDateTime end, Employee employee) {
        return repairsRepository.findAllRepairByDateOfIssueBetweenAndEmployee(start, end, employee.getId());
    }

    @Transactional
    public void save(Repair repair) {
        System.err.println("RepairService save");
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
        enrichUpdateIssue(updateRepair, id);
        repairsRepository.save(updateRepair);
        savingData(updateRepair);
    }

    @Transactional
    public void cancelledIssue(Long id) {
        System.err.println("RepairService cancelledIssue");
        Repair updateRepair = (Repair) getPresentOptional(repairsRepository.findById(id));
        enrichCancelledIssueRepair(updateRepair);
        repairsRepository.save(updateRepair);
    }

    @Transactional
    public void editRepair(Repair updateRepair, Long id) {
        repairsRepository.save(enrichEdit(updateRepair, id));
    }

    @Transactional
    public void delete(Long id) {
        System.err.println("RepairService delete");
        Repair deleteRepair = (Repair) getPresentOptional(repairsRepository.findById(id));
        if (!deleteRepair.getIsDone()) {
            repairsRepository.delete(deleteRepair);
            System.err.println("удален ремонт");
            savingData(deleteRepair);
            System.err.println("удален ремонт из данных");
        }
    }

    private Repair enrichNew(Repair repairFromFront) {
//        System.err.println("repairFromFront: " + repairFromFront);
        Repair newRepair = createRepairEmpty();
        if (repairFromFront.getDateOfAcceptance() == null) {
            newRepair.setDateOfAcceptance(LocalDateTime.now());
        } else {
            newRepair.setDateOfAcceptance(repairFromFront.getDateOfAcceptance());
        }
        newRepair.setPhoneNumber(repairFromFront.getPhoneNumber());
        newRepair.setSeller(repairFromFront.getSeller());
        newRepair.setShop(repairFromFront.getShop());
        newRepair.setAnnotation("Приём ремонта:\n" + repairFromFront.getAnnotation());
//        System.err.println("newRepair: " + newRepair);
        return newRepair;
    }

    private Repair enrichEdit(Repair repair, Long id) {
        Repair editRepair = (Repair) getPresentOptional(repairsRepository.findById(id));
        if (repair.getPhoneNumber() != null) editRepair.setPhoneNumber(repair.getPhoneNumber());
        if (repair.getRepairsRevenue() != null) editRepair.setRepairsRevenue(repair.getRepairsRevenue());
        if (repair.getRepairsPurchase() != null) editRepair.setRepairsPurchase(repair.getRepairsPurchase());
        if (repair.getMaster() != null) editRepair.setMaster(repair.getMaster());
        return editRepair;
    }

    private void enrichUpdateIssue(Repair updateRepair, Long id) {
        Repair repairFromDB = (Repair) getPresentOptional(repairsRepository.findById(id));
        if (repairFromDB.getIsDone()) {
//            System.err.println("repairFromDB: " + repairFromDB);
//            System.err.println("updateRepair: " + updateRepair);
            updateRepair.setId(id);
            updateRepair.setSeller(repairFromDB.getSeller());
            updateRepair.setShop(repairFromDB.getShop());
            updateRepair.setPhoneNumber(repairFromDB.getPhoneNumber());
            updateRepair.setDateOfAcceptance(repairFromDB.getDateOfAcceptance());
            updateRepair.setIsDone(true);
            updateRepair.setDateOfDone(repairFromDB.getDateOfDone());
            updateRepair.setAnnotation(repairFromDB.getAnnotation() + "\nВыдача ремонта:\n" + updateRepair.getAnnotation());
            updateRepair.setDateOfIssue(LocalDateTime.now());
            updateRepair.setRepairsProfit(updateRepair.getRepairsRevenue() - updateRepair.getRepairsPurchase());
            updateRepair.setSellerSalary(salaryCalculation(updateRepair).get(0));
            updateRepair.setMasterSalary(salaryCalculation(updateRepair).get(1));
        }
    }

    private List<Double> salaryCalculation(Repair repair) {
        int sellerPercent = 10;// TODO времянка
        int masterPercent = 45;// TODO времянка
        long employeeId = repair.getEmployee().getId();
        long sellerId = repair.getSeller().getId();
        long masterId = repair.getMaster().getId();
        int repairProfit = repair.getRepairsProfit();
        double masterSalary = (repairProfit * masterPercent / 100d + repairProfit * sellerPercent / 100d / 2);
        List<Double> salaryList = new ArrayList<>();
        if (employeeId == masterId && sellerId == masterId) {
            //* Если принимающий, выдающий и мастер -- один человек
            salaryList.add(0, 0.0);
            salaryList.add(1, masterSalary);
        }
        if (sellerId == masterId && employeeId != masterId) {
            //* Если принимающий и мастер один, выдающий -- другой
            salaryList.add(0, 0.0);
            salaryList.add(1, masterSalary);
        }
        if (employeeId == sellerId && employeeId != masterId) {
            //* Если принимающий и выдающий один, мастер -- другой
            salaryList.add(0, repairProfit * sellerPercent / 100d);
            salaryList.add(1, repairProfit * masterPercent / 100d);
        }
        if (employeeId == masterId && sellerId != masterId) {
            //* Если принимающий один, выдающий и мастер -- другой
            salaryList.add(0, repairProfit * sellerPercent / 100d);
            salaryList.add(1, repairProfit * masterPercent / 100d);
        }
        if (employeeId != masterId && employeeId != sellerId && sellerId != masterId) {
            //* Если принимающий, выдающий и мастер -- разные люди*/
            salaryList.add(0, repairProfit * sellerPercent / 100d);
            salaryList.add(1, repairProfit * masterPercent / 100d);
        }
        return salaryList;
    }

    private void enrichCancelledIssueRepair(Repair repair) {
        if (repair.getIsDone()) {
            ArrayList<Long> employees = new ArrayList<>();
            employees.add(repair.getEmployee().getId());
            employees.add(repair.getSeller().getId());
            employees.add(repair.getMaster().getId());
            dataService.cancelIssueFromRepair(repair, removeDuplicates(employees));
            repair.setRepairsRevenue(0);
            repair.setRepairsPurchase(0);
            repair.setRepairsProfit(0);
            repair.setSellerSalary(0d);
            repair.setMasterSalary(0d);
            repair.setDateOfIssue(null);
            repair.setAnnotation(repair.getAnnotation().substring(0, repair.getAnnotation().indexOf("\nВыдача ремонта:")));
            repair.setEmployee(null);
            repair.setMaster(null);
        }
    }

    private void savingData(Repair repair) {
        System.err.println(repair);
        long employeeId = repair.getEmployee().getId();// TODO времянка
        long sellerId = repair.getSeller().getId();// TODO времянка
        long masterId = repair.getMaster().getId();// TODO времянка
        LocalDateTime start = dateTimeToStartDayPlusMin(repair.getDateOfIssue());
        LocalDateTime end = dateTimeToEndDayMinusNano(repair.getDateOfIssue());
        System.err.println("ЦИКЛВЫ");
        findAllRepairByDateOfIssueBetweenAndEmployee(start, end, repair.getEmployee()).forEach(System.err::println);
        if (employeeId == masterId && sellerId == masterId) {
            //* Если принимающий, выдающий и мастер -- один человек
            System.err.println("Если принимающий, выдающий и мастер -- один человек");
            dataService.saveForRepair(start, repair.getMaster(),
                    findAllRepairByDateOfIssueBetweenAndEmployee(start, end, repair.getMaster()), repair, true);
        }
        if (sellerId == masterId && employeeId != masterId) {
            //* Если принимающий и мастер один, выдающий -- другой
            System.err.println("Если принимающий и мастер один, выдающий -- другой");
            dataService.saveForRepair(start, repair.getEmployee(),
                    findAllRepairByDateOfIssueBetweenAndEmployee(start, end, repair.getEmployee()), repair, true);
            dataService.saveForRepair(start, repair.getMaster(),
                    findAllRepairByDateOfIssueBetweenAndEmployee(start, end, repair.getMaster()), repair, false);
        }
        if (employeeId == sellerId && employeeId != masterId) {
            //* Если принимающий и выдающий один, мастер -- другой
            System.err.println("Если принимающий и выдающий один, мастер -- другой");
            dataService.saveForRepair(start, repair.getSeller(),
                    findAllRepairByDateOfIssueBetweenAndEmployee(start, end, repair.getSeller()), repair, true);
            dataService.saveForRepair(start, repair.getMaster(),
                    findAllRepairByDateOfIssueBetweenAndEmployee(start, end, repair.getMaster()), repair, false);
        }
        if (employeeId == masterId && sellerId != masterId) {
            //* Если принимающий один, выдающий и мастер -- другой
            System.err.println("Если принимающий один, выдающий и мастер -- другой");
            dataService.saveForRepair(start, repair.getSeller(),
                    findAllRepairByDateOfIssueBetweenAndEmployee(start, end, repair.getSeller()), repair, false);
            dataService.saveForRepair(start, repair.getMaster(),
                    findAllRepairByDateOfIssueBetweenAndEmployee(start, end, repair.getMaster()), repair, true);
        }
        if (employeeId != masterId && employeeId != sellerId && sellerId != masterId) {
            //* Если принимающий, выдающий и мастер -- разные люди
            System.err.println("Если принимающий, выдающий и мастер -- разные люди");
            dataService.saveForRepair(start, repair.getEmployee(),
                    findAllRepairByDateOfIssueBetweenAndEmployee(start, end, repair.getEmployee()), repair, true);
            dataService.saveForRepair(start, repair.getSeller(),
                    findAllRepairByDateOfIssueBetweenAndEmployee(start, end, repair.getSeller()), repair, false);
            dataService.saveForRepair(start, repair.getMaster(),
                    findAllRepairByDateOfIssueBetweenAndEmployee(start, end, repair.getMaster()), repair, false);
        }
    }

    private Repair createRepairEmpty() {
        return new Repair("", 0, 0, 0, 0d, 0d,
                null, null, null, false, "", null,
                null, null, null);
    }

}