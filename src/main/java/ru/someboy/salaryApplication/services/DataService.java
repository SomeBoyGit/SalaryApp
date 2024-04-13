package ru.someboy.salaryApplication.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.someboy.salaryApplication.models.*;
import ru.someboy.salaryApplication.repositories.DataRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static ru.someboy.salaryApplication.util.Methods.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DataService {
//    private final EmployeesService employeesService;
//    private final DataRepository dataRepository;
//    private final DataFullMonthService dataFullMonthService;
//    private final DataHalfMonthService dataHalfMonthService;
//
//    public List<Data> findAll() {
//        return dataRepository.findAll();
//    }
//
//    public Data findOne(Long id) {
//        return dataRepository.findById(id).orElse(null);
//    }
//
//    public Optional<Data> findDataByDateOfDataAndEmployee(LocalDateTime dateOfData, Employee employee) {
//        return dataRepository.findDataByDateOfDataAndEmployee(dateOfData, employee);
//    }
//
//    public List<Data> getDataBetweenDates(LocalDateTime dateOfData, Employee employee) {
//        return dataRepository.findAllDataByDateOfDataBetweenAndEmployee(
//                startAndEndDateTimeList(dateOfData).get(0), startAndEndDateTimeList(dateOfData).get(1),
//                employee);
//    }
//
//    public List<Data> getDataBetweenDatesHalfMonth(LocalDateTime dateOfData, Employee employee) {
//        return dataRepository.findAllDataByDateOfDataBetweenAndEmployee(
//                startAndEndDateTimeListHalfMonth(dateOfData).get(0), startAndEndDateTimeListHalfMonth(dateOfData).get(1),
//                employee);
//    }
//
//    @Transactional
//    public void save(Data data) {
//        if (data.getDateOfData() == null) {
//            data.setDateOfData(dateTimeToStartDayPlusMin(LocalDateTime.now()));
//        } else {
//            data.setDateOfData(dateTimeToStartDayPlusMin(data.getDateOfData()));
//        }
//        Optional<Data> optionalData = findDataByDateOfDataAndEmployee(data.getDateOfData(), data.getEmployee());
//        if (optionalData.isPresent()) {
//            update(data, optionalData.get().getId());
//        } else {
//            System.err.println("DataService save");
//            enrichData(data, false, null);
//            dataRepository.save(data);
//            dataHalfMonthService.save(getDataBetweenDatesHalfMonth(data.getDateOfData(), data.getEmployee()));
//            dataFullMonthService.save(getDataBetweenDates(data.getDateOfData(), data.getEmployee()));
//        }
//    }
//
//    @Transactional
//    public void saveForRepair(LocalDateTime start, Employee employee, List<Repair> repairs, Repair repair, boolean isIssue) {
//        Optional<Data> optionalData = findDataByDateOfDataAndEmployee(start, employee);
//        if (optionalData.isPresent()) {
//            updateForRepair(start, optionalData.get(), repairs, repair, isIssue);
//        } else {
//            System.err.println("DataService saveForRepair");
//            Data data = createOrUpdateDataForRepair(start, createDataEmpty(), employee, repairs, repair, isIssue);
//            System.err.println("DataService saveForRepair Data: " + data);
//            dataRepository.save(data);
//            dataHalfMonthService.save(getDataBetweenDatesHalfMonth(data.getDateOfData(), data.getEmployee()));
//            dataFullMonthService.save(getDataBetweenDates(data.getDateOfData(), data.getEmployee()));
//        }
//    }
//
//    @Transactional
//    public void update(Data updateData, Long id) {
//        System.err.println("DataService update");
//        System.err.println("DataService update Data: " + updateData);
//        enrichData(updateData, true, id);
//        dataRepository.save(updateData);
//        dataHalfMonthService.save(getDataBetweenDatesHalfMonth(updateData.getDateOfData(), updateData.getEmployee()));
//        dataFullMonthService.save(getDataBetweenDates(updateData.getDateOfData(), updateData.getEmployee()));
//
//    }
//
//    @Transactional
//    public void updateForRepair(LocalDateTime start, Data updateData, List<Repair> repairs, Repair repair, boolean isIssue) {
//        System.err.println("DataService updateForRepair");
////        if(updateData.getRepairsCount())
//        updateData = createOrUpdateDataForRepair(start, updateData, updateData.getEmployee(), repairs, repair, isIssue);
//        System.err.println("DataService updateForRepair Data: " + updateData);
//
//        dataRepository.save(updateData);
//        dataHalfMonthService.save(getDataBetweenDatesHalfMonth(updateData.getDateOfData(), updateData.getEmployee()));
//        dataFullMonthService.save(getDataBetweenDates(updateData.getDateOfData(), updateData.getEmployee()));
//    }
//
//    @Transactional
//    public void delete(Long id) {
//        System.err.println("DataService delete");
//        Optional<Data> dataOptional = dataRepository.findById(id);
//        if (dataOptional.isPresent()) {
//            Data data = dataOptional.get();
//            boolean haveRepairEmployee = (data.getAllRevenue() - data.getAccessoriesRevenue() + data.getDevicesRevenue() + data.getRepairsRevenue()) > 0;
//            boolean haveRepairSellerOrMaster = data.getRepairsRevenue() > 0;
//            if(haveRepairEmployee || haveRepairSellerOrMaster) {
//                Data updateData = createDataEmpty();
//                updateData.setId(data.getId());
//                updateData.setRepairsCount(data.getRepairsCount());
//                updateData.setRepairsRevenue(data.getRepairsRevenue());
//                updateData.setRepairsPurchase(data.getRepairsPurchase());
//                updateData.setRepairsProfit(data.getRepairsProfit());
//                updateData.setRepairsSalary(data.getRepairsSalary());
//                updateData.setSalary();
//                if(haveRepairEmployee) {
//                    updateData.setAllRevenue(data.getAllRevenue() - data.getAccessoriesRevenue() + data.getDevicesRevenue() + data.getRepairsRevenue());
//                }
//                if(haveRepairSellerOrMaster) {
//
//                }
//                dataRepository.save(updateData);
//                dataHalfMonthService.save(getDataBetweenDatesHalfMonth(updateData.getDateOfData(), updateData.getEmployee()));
//                dataFullMonthService.save(getDataBetweenDates(updateData.getDateOfData(), updateData.getEmployee()));
//            } else {
//                DataFullMonth dataFullMonth = (DataFullMonth) getPresentOptional(
//                        dataFullMonthService.findDFMByDateAndEmployee(startAndEndDateTimeList(data.getDateOfData()).get(0), data.getEmployee()));
//                dataRepository.deleteById(id);
//                if (dataFullMonth.getCountDay() == 1) {
//                    dataFullMonthService.delete(dataFullMonth.getId());
//                } else {
//                    dataFullMonthService.save(getDataBetweenDates(data.getDateOfData(), data.getEmployee()));
//                }
//                DataHalfMonth dataHalfMonth = (DataHalfMonth) getPresentOptional(
//                        dataHalfMonthService.findDHMByDateAndEmployee(startAndEndDateTimeListHalfMonth(data.getDateOfData()).get(0), data.getEmployee()));
//                if (dataFullMonth.getCountDay() == 1) {
//                    dataHalfMonthService.delete(dataHalfMonth.getId());
//                } else {
//                    dataHalfMonthService.save(getDataBetweenDatesHalfMonth(data.getDateOfData(), data.getEmployee()));
//                }
//            }
//        }
//    }
//
//    private void enrichData(Data data, boolean isUpdate, Long id) {
//        if (data.getDateOfData() == null) {
//            dateTimeToStartDayPlusMin(LocalDateTime.now());
//        } else {
//            data.setDateOfData(dateTimeToStartDayPlusMin(data.getDateOfData()));
//        }
//        if (isUpdate) {
//            Data updateData = (Data) getPresentOptional(dataRepository.findById(id));
//            data.setDateOfData(updateData.getDateOfData());
//            data.setEmployee(updateData.getEmployee());
//            data.setId(id);
//        }
//        Shop shop = data.getShop();
//        data.setDateOfEntry(LocalDateTime.now());
//        data.setAccessoriesSalary(data.getAccessoriesRevenue() * shop.getAccessoriesPercent() / 100.0);
//        data.setBonus(data.getAccessoriesRevenue() / 3000 * 100);
//        data.setMarketingMarginProfit(data.getMarketingMargin() - percentageOf(data.getMarketingMargin(), shop.getAccessoriesPercent()));
//        data.setMarketingMarginSalary(data.getMarketingMarginProfit() / 2);
//        data.setSimSalary(data.getSimRevenue() * shop.getSimPercent() / 100.0);
//        data.setDevicesProfit(data.getDevicesRevenue() - data.getDevicesPurchase());
//        data.setDevicesSalary(data.getDevicesProfit() * shop.getDevicesPercent() / 100.0);
//        if (data.getRepairsCount() == null) data.setRepairsCount(0);
//        if (data.getRepairsRevenue() == null) data.setRepairsRevenue(0);
//        if (data.getRepairsPurchase() == null) data.setRepairsPurchase(0);
//        if (data.getRepairsProfit() == null) data.setRepairsProfit(0);
//        if (data.getRepairsSalary() == null) data.setRepairsSalary(0d);
//        data.setSalary(data.getAccessoriesSalary() + data.getMarketingMarginSalary() +
//                data.getBonus() + data.getSimSalary() + data.getDevicesSalary() + data.getRepairsSalary());
//        data.setAllRevenue(getAllRevenue(data.getAllRevenue(), data.getAccessoriesRevenue(), data.getDevicesRevenue(), data.getRepairsRevenue()));
//        System.err.println("enrichData: " + data);
//    }
//
//    private double getAllRevenue(Double allRevenue, Integer accessoriesRevenue, Integer devicesRevenue, Integer repairsRevenue) {
//        if (allRevenue == null || allRevenue == 0) {
//            return accessoriesRevenue + devicesRevenue + repairsRevenue;
//        } else {
//            return allRevenue - accessoriesRevenue + devicesRevenue + repairsRevenue;
//        }
//    }
//
//    private Data createDataEmpty() {
//        LocalDateTime dateTime = LocalDateTime.MIN;
//        return new Data(
//                0, 0, 0d, 0, 0, 0d,
//                0d, 0, 0, 0d, 0, 0, 0,
//                0, 0d, 0, 0, 0, 0, 0d,
//                dateTime, dateTime, 0, "", 0, 0, 0d, 0d
//        );
//    }
//
//    public Data createOrUpdateDataForRepair(LocalDateTime start, Data data, Employee employee, List<Repair> repairs, Repair repair, boolean isIssue) {
//
//        RepairSum repairSum = new RepairSum();
//        repairSum = repairSum.getRepairSum(repairs, employee);
//        System.err.println("DataService createOrUpdateDataForRepair repairSum: " + repairSum);
//        data.setEmployee(employee);
//        if (data.getShop() == null) {
//            data.setShop(repair.getShop());
//        }
//        data.setDateOfEntry(LocalDateTime.now());
//        data.setDateOfData(start);
//        if (isIssue) {
//            data.setAllRevenue(data.getAllRevenue() + repair.getRepairsRevenue());
//        }
//        if (Objects.equals(employee.getId(), repair.getSeller().getId())
//                || Objects.equals(employee.getId(), repair.getMaster().getId())) {
//            data.setRepairsCount(repairSum.getCountRepairs());// к имеющейся сумме прибавляем новый
//            data.setRepairsRevenue(repairSum.getRepairsRevenueSum());// к имеющейся сумме прибавляем новый
//            data.setRepairsPurchase(repairSum.getRepairsPurchaseSum());// к имеющейся сумме прибавляем новый
//            data.setRepairsProfit(repairSum.getRepairsProfitSum());// к имеющейся сумме прибавляем новый
//            data.setRepairsSalary(repairSum.getSalarySum());
//        }
//        data.setSalary(data.getAccessoriesSalary() + data.getMarketingMarginSalary() +
//                data.getBonus() + data.getSimSalary() + data.getDevicesSalary() + data.getRepairsSalary());
//        System.err.println("DataService createOrUpdateDataForRepair data: " + data);
//        return data;
//    }
//
//    public void cancelIssueFromRepair(Repair repair, List<Long> workersIdList) {
//        LocalDateTime start = dateTimeToStartDayPlusMin(repair.getDateOfIssue());
////        LocalDateTime end = dateTimeToEndDayMinusNano(repair.getDateOfIssue());
//        long employeeId = repair.getEmployee().getId();
//        long sellerId = repair.getSeller().getId();
//        long masterId = repair.getMaster().getId();
//        workersIdList.forEach(workerId -> {
//            Data data = (Data) getPresentOptional(findDataByDateOfDataAndEmployee(start, employeesService.findOne(workerId)));
//            double repairRevenue = repair.getRepairsRevenue();
//            System.err.println("repairRevenue1: " + repairRevenue);
//            if (workerId == masterId || workerId == sellerId) {
//                data.setRepairsCount(data.getRepairsCount() - 1);
//                data.setRepairsRevenue(data.getRepairsRevenue() - repair.getRepairsRevenue());
//                data.setRepairsPurchase(data.getRepairsPurchase() - repair.getRepairsPurchase());
//                data.setRepairsProfit(data.getRepairsProfit() - repair.getRepairsProfit());
//                if (workerId == masterId) {
//                    data.setRepairsSalary(repairRevenue - repair.getMasterSalary());
//                    data.setSalary(data.getSalary() - repair.getMasterSalary());
//                }
//                if (workerId == sellerId) {
//                    data.setRepairsSalary(repairRevenue - repair.getSellerSalary());
//                    data.setSalary(data.getSalary() - repair.getSellerSalary());
//                }
//                if (data.getRepairsRevenue() == 0) {
//                    delete(data.getId());
//                    return;
//                }
//            }
//            if (workerId == employeeId) {
//                System.err.println("Вошли в If employeeId");
//                data.setAllRevenue(data.getAllRevenue() - repairRevenue);
//                System.err.println("data.getAllRevenue(): " + data.getAllRevenue());
//                System.err.println("repairRevenue2: " + repairRevenue);
//                System.err.println("data.getAllRevenue() - repairRevenue: " + (data.getAllRevenue() - repairRevenue));
//                if (data.getAllRevenue() == 0) {
//                    delete(data.getId());
//                    return;
//                }
//            }
//            System.err.println("data: " + data);
//            dataRepository.save(data);
//            dataHalfMonthService.save(getDataBetweenDatesHalfMonth(start, data.getEmployee()));
//            dataFullMonthService.save(getDataBetweenDates(start, data.getEmployee()));
//        });
//    }
}
