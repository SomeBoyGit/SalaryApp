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
public class RepairsService {
//    private final RepairsRepository repairsRepository;
//    private final DataService dataService;
//
//    public List<Repair> findAll() {
//        return repairsRepository.findAll();
//    }
//
//    public Repair findOne(long id) {
//        return repairsRepository.findById(id).orElse(null);
//    }
//
//    public List<Repair> findAllRepairByDateOfIssueBetweenAndEmployee(LocalDateTime start, LocalDateTime end, Employee employee) {
//        return repairsRepository.findAllRepairByDateOfIssueBetweenAndEmployee(start, end, employee.getId());
//    }
//
//    @Transactional
//    public void save(Repair repair) {
//        System.err.println("RepairService save");
//        repairsRepository.save(enrichNew(repair));
//    }
//
//    @Transactional
//    public void updateDoneRepair(Long id) {
//        Repair updateRepair = (Repair) getPresentOptional(repairsRepository.findById(id));
//        updateRepair.setIsDone(true);
//        updateRepair.setDateOfDone(LocalDateTime.now());
//        repairsRepository.save(updateRepair);
//    }
//
//    @Transactional
//    public void updateCancelDoneRepair(long id) {
//        Repair updateRepair = (Repair) getPresentOptional(repairsRepository.findById(id));
//        updateRepair.setIsDone(false);
//        updateRepair.setDateOfDone(null);
//        repairsRepository.save(updateRepair);
//    }
//
//    @Transactional
//    public void updateIssueRepair(Repair updateRepair, Long id) {
//        System.err.println("1RepairService updateIssueRepair");
//        System.err.println("1updateRepair.getEmployee().getId(): " + updateRepair.getEmployee().getId());
//        System.err.println("1updateRepair.getMaster().getId(): " + updateRepair.getMaster().getId());
//        updateRepair = enrichUpdateIssue(updateRepair, id);
//        System.err.println("2RepairService updateIssueRepair");
//        System.err.println("2updateRepair.getEmployee().getId(): " + updateRepair.getEmployee().getId());
//        System.err.println("2updateRepair.getMaster().getId(): " + updateRepair.getMaster().getId());
//        savingData(updateRepair);
//        System.err.println("3RepairService updateIssueRepair");
//        System.err.println("3updateRepair.getEmployee().getId(): " + updateRepair.getEmployee().getId());
//        System.err.println("3updateRepair.getMaster().getId(): " + updateRepair.getMaster().getId());
//        repairsRepository.save(updateRepair);
//    }
//
//    @Transactional
//    public void cancelledIssue(Long id) {
//        System.err.println("RepairService cancelledIssue");
//        Repair updateRepair = (Repair) getPresentOptional(repairsRepository.findById(id));
//        repairsRepository.save(enrichCancelledIssueRepair(updateRepair));
////        savingData(updateRepair);
////        LocalDateTime start = dateTimeToStartDayPlusMin(updateRepair.getDateOfIssue());
////        LocalDateTime end = dateTimeToEndDayMinusNano(updateRepair.getDateOfIssue());
////        dataService.saveForRepair(start, updateRepair.getEmployee(),
////                findAllRepairByDateOfIssueBetweenAndEmployee(start, end, updateRepair.getEmployee()), updateRepair, false);
//    }
//
//    @Transactional
//    public void editRepair(Repair updateRepair, Long id) {
//        repairsRepository.save(enrichEdit(updateRepair, id));
//    }
//
//    @Transactional
//    public void delete(Long id) {
//        System.err.println("RepairService delete");
//        Repair deleteRepair = (Repair) getPresentOptional(repairsRepository.findById(id));
//        repairsRepository.delete(deleteRepair);
//        savingData(deleteRepair);
//    }
//
//    private Repair enrichNew(Repair newRepair) {
//        if(newRepair.getDateOfAcceptance() == null) {
//            newRepair.setDateOfAcceptance(LocalDateTime.now());
//        }
//        newRepair.setAnnotation("Приём ремонта:\n" + newRepair.getAnnotation());
//        newRepair.setIsDone(false);
//        return newRepair;
//    }
//
//    private Repair enrichEdit(Repair repair, Long id) {
//        Repair editRepair = (Repair) getPresentOptional(repairsRepository.findById(id));
//        if (repair.getPhoneNumber() != null) editRepair.setPhoneNumber(repair.getPhoneNumber());
//        if (repair.getRepairsRevenue() != null) editRepair.setRepairsRevenue(repair.getRepairsRevenue());
//        if (repair.getRepairsPurchase() != null) editRepair.setRepairsPurchase(repair.getRepairsPurchase());
//        if (repair.getMaster() != null) editRepair.setMaster(repair.getMaster());
//        return editRepair;
//    }
//
//    private Repair enrichUpdateIssue(Repair updateRepair, Long id) {
//        Repair repairFromDB = (Repair) getPresentOptional(repairsRepository.findById(id));
//        if (repairFromDB.getIsDone()) {
//            repairFromDB.setRepairsRevenue(updateRepair.getRepairsRevenue());
//            repairFromDB.setRepairsPurchase(updateRepair.getRepairsPurchase());
//            repairFromDB.setDateOfIssue(LocalDateTime.now());
//            repairFromDB.setAnnotation(repairFromDB.getAnnotation() + "\nВыдача ремонта:\n" + updateRepair.getAnnotation());
//            repairFromDB.setEmployee(updateRepair.getEmployee());
//            repairFromDB.setMaster(updateRepair.getMaster());
//            repairFromDB.setRepairsProfit(updateRepair.getRepairsRevenue() - updateRepair.getRepairsPurchase());
//            repairFromDB.setSellerSalary(salaryCalculation(repairFromDB.getRepairsProfit(), repairFromDB.getEmployee(),
//                    repairFromDB.getSeller(), updateRepair.getMaster()).get(0));
//            repairFromDB.setMasterSalary(salaryCalculation(repairFromDB.getRepairsProfit(), repairFromDB.getEmployee(),
//                    repairFromDB.getSeller(), updateRepair.getMaster()).get(1));
//        }
//        return repairFromDB;
//    }
//
//    private List<Double> salaryCalculation(Integer repairProfit, Employee employee, Employee seller, Employee master) {
//        int sellerPercent = 10;// TODO времянка
//        int masterPercent = 45;// TODO времянка
//        long employeeId = employee.getId();// TODO времянка
//        long sellerId = seller.getId();// TODO времянка
//        long masterId = master.getId();// TODO времянка
//        double masterSalary = (repairProfit * masterPercent / 100d + repairProfit * sellerPercent / 100d / 2);
//        List<Double> salaryList = new ArrayList<>();
//        if (employeeId == masterId && sellerId == masterId) {
//            //* Если принимающий, выдающий и мастер -- один человек
//            salaryList.add(0, 0.0);
//            salaryList.add(1, masterSalary);
//        }
//        if (sellerId == masterId && employeeId != masterId) {
//            //* Если принимающий и мастер один, выдающий -- другой
//            salaryList.add(0, 0.0);
//            salaryList.add(1, masterSalary);
//        }
//        if (employeeId == sellerId && employeeId != masterId) {
//            //* Если принимающий и выдающий один, мастер -- другой
//            salaryList.add(0, repairProfit * sellerPercent / 100d);
//            salaryList.add(1, repairProfit * masterPercent / 100d);
//        }
//        if (employeeId == masterId && sellerId != masterId) {
//            //* Если принимающий один, выдающий и мастер -- другой
//            salaryList.add(0, repairProfit * sellerPercent / 100d);
//            salaryList.add(1, repairProfit * masterPercent / 100d);
//        }
//        if (employeeId != masterId && employeeId != sellerId && sellerId != masterId) {
//            //* Если принимающий, выдающий и мастер -- разные люди*/
//            salaryList.add(0, repairProfit * sellerPercent / 100d);
//            salaryList.add(1, repairProfit * masterPercent / 100d);
//        }
//        return salaryList;
//    }
//
//    /*TODO
//     *  Нужно создавать Data при выдаче ремонта
//     *  Если Data уже создана, то обновляем её добавляя СУММУ всех ремонтов этого сотрудника
//     *  и пересчитываем зарплату
//     *  Ещё нужно пересчитывать Data, если ремонт отменён,
//     *  если отменён последний ремонт, а в Data были только ремонты - Data УДАЛИТЬ.
//     *  Создавать и удалять в DataService, не здесь.....*/
//
//
//    private Repair enrichCancelledIssueRepair(Repair repair) {
//        if (repair.getIsDone()) {
//            ArrayList <Long> employees = new ArrayList<>();
//            employees.add(repair.getEmployee().getId());
//            employees.add(repair.getSeller().getId());
//            employees.add(repair.getMaster().getId());
//            dataService.cancelIssueFromRepair(repair, removeDuplicates(employees));
//            repair.setRepairsRevenue(0);
//            repair.setRepairsPurchase(0);
//            repair.setDateOfIssue(null);
//            repair.setAnnotation(repair.getAnnotation().substring(0, repair.getAnnotation().indexOf("\nВыдача ремонта:")));
//            repair.setEmployee(null);
//            repair.setMaster(null);
//            repair.setRepairsProfit(0);
//            repair.setSellerSalary(0d);
//            repair.setMasterSalary(0d);
//        }
//        return repair;
//    }
//
//    private void savingData(Repair repair) {
//        System.err.println(repair);
//        long employeeId = repair.getEmployee().getId();// TODO времянка
//        long sellerId = repair.getSeller().getId();// TODO времянка
//        long masterId = repair.getMaster().getId();// TODO времянка
//        System.err.println("employeeId: " + employeeId);
//        System.err.println("sellerId: " + sellerId);
//        System.err.println("masterId: " + masterId);
//        LocalDateTime start = dateTimeToStartDayPlusMin(repair.getDateOfIssue());
//        LocalDateTime end = dateTimeToEndDayMinusNano(repair.getDateOfIssue());
//        if (employeeId == masterId && sellerId == masterId) {
//            //* Если принимающий, выдающий и мастер -- один человек
//            System.err.println("Если принимающий, выдающий и мастер -- один человек");
//            dataService.saveForRepair(start, repair.getMaster(),
//                    findAllRepairByDateOfIssueBetweenAndEmployee(start, end, repair.getMaster()), repair, true);
//        }
//        if (sellerId == masterId && employeeId != masterId) {
//            //* Если принимающий и мастер один, выдающий -- другой
//            System.err.println("Если принимающий и мастер один, выдающий -- другой");
//            dataService.saveForRepair(start, repair.getEmployee(),
//                    findAllRepairByDateOfIssueBetweenAndEmployee(start, end, repair.getEmployee()), repair, true);
//            dataService.saveForRepair(start, repair.getMaster(),
//                    findAllRepairByDateOfIssueBetweenAndEmployee(start, end, repair.getMaster()), repair, false);
//        }
//        if (employeeId == sellerId && employeeId != masterId) {
//            //* Если принимающий и выдающий один, мастер -- другой
//            System.err.println("Если принимающий и выдающий один, мастер -- другой");
//            dataService.saveForRepair(start, repair.getSeller(),
//                    findAllRepairByDateOfIssueBetweenAndEmployee(start, end, repair.getSeller()), repair, true);
//            dataService.saveForRepair(start, repair.getMaster(),
//                    findAllRepairByDateOfIssueBetweenAndEmployee(start, end, repair.getMaster()), repair, false);
//        }
//        if (employeeId == masterId && sellerId != masterId) {
//            //* Если принимающий один, выдающий и мастер -- другой
//            System.err.println("Если принимающий один, выдающий и мастер -- другой");
//            dataService.saveForRepair(start, repair.getSeller(),
//                    findAllRepairByDateOfIssueBetweenAndEmployee(start, end, repair.getSeller()), repair, false);
//            dataService.saveForRepair(start, repair.getMaster(),
//                    findAllRepairByDateOfIssueBetweenAndEmployee(start, end, repair.getMaster()), repair, true);
//        }
//        if (employeeId != masterId && employeeId != sellerId && sellerId != masterId) {
//            //* Если принимающий, выдающий и мастер -- разные люди
//            System.err.println("Если принимающий, выдающий и мастер -- разные люди");
//            dataService.saveForRepair(start, repair.getEmployee(),
//                    findAllRepairByDateOfIssueBetweenAndEmployee(start, end, repair.getEmployee()), repair, true);
//            dataService.saveForRepair(start, repair.getSeller(),
//                    findAllRepairByDateOfIssueBetweenAndEmployee(start, end, repair.getSeller()), repair, false);
//            dataService.saveForRepair(start, repair.getMaster(),
//                    findAllRepairByDateOfIssueBetweenAndEmployee(start, end, repair.getMaster()), repair, false);
//        }
//    }
}