package ru.someboy.salaryApplication.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.someboy.salaryApplication.models.Data;
import ru.someboy.salaryApplication.models.DataHalfMonth;
import ru.someboy.salaryApplication.models.Employee;
import ru.someboy.salaryApplication.repositories.DataHalfMonthRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import static ru.someboy.salaryApplication.util.Methods.startAndEndDateTimeListHalfMonth;

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

    public Optional<DataHalfMonth> findDHMByDateAndEmployee(LocalDateTime date, Employee employee) {
        return dhmRepository.findDataHalfMonthByDateAndEmployee(date, employee);
    }

    @Transactional
    public void save(List<Data> dataList) {
        Optional<DataHalfMonth> optionalDHM = findDHMByDateAndEmployee(
                startAndEndDateTimeListHalfMonth(dataList.get(0).getDateOfData()).get(0), dataList.get(0).getEmployee());
        if (optionalDHM.isPresent()) {
            update(dataList, optionalDHM.get().getId());
        } else {
            dhmRepository.save(getDataHalfMonthFromDataList(dataList));
        }
    }

    @Transactional
    public void update(List<Data> dataList, Long id) {
        DataHalfMonth dataHalfMonth = getDataHalfMonthFromDataList(dataList);
        dataHalfMonth.setId(id);
        dhmRepository.save(dataHalfMonth);
    }

    @Transactional
    public void delete(long id) {
        dhmRepository.deleteById(id);
    }

    private DataHalfMonth getDataHalfMonthFromDataList(List<Data> dataList) {
        DataHalfMonth dhm = createEmptyDataFullMonth();
        if (!dataList.isEmpty()) {
            AtomicLong count = new AtomicLong(0L);
            dataList.forEach(data -> {
                dhm.setCountDay(count.incrementAndGet());
                dhm.setAccessoriesCount(dhm.getAccessoriesCount() + data.getAccessoriesCount());
                dhm.setAccessoriesRevenue(dhm.getAccessoriesRevenue() + data.getAccessoriesRevenue());
                dhm.setAccessoriesSalary(dhm.getAccessoriesSalary() + data.getAccessoriesSalary());
                dhm.setBonus(dhm.getBonus() + data.getBonus());
                dhm.setMarketingMargin(dhm.getMarketingMargin() + data.getMarketingMargin());
                dhm.setMarketingMarginProfit(dhm.getMarketingMarginProfit() + data.getMarketingMarginProfit());
                dhm.setMarketingMarginSalary(dhm.getMarketingMarginSalary() + data.getMarketingMarginSalary());
                dhm.setSimCount(dhm.getSimCount() + data.getSimCount());
                dhm.setSimRevenue(dhm.getSimRevenue() + data.getSimRevenue());
                dhm.setSimSalary(dhm.getSimSalary() + data.getSimSalary());
                dhm.setDevicesCount(dhm.getDevicesCount() + data.getDevicesCount());
                dhm.setDevicesRevenue(dhm.getDevicesRevenue() + data.getDevicesRevenue());
                dhm.setDevicesPurchase(dhm.getDevicesPurchase() + data.getDevicesPurchase());
                dhm.setDevicesProfit(dhm.getDevicesProfit() + data.getDevicesProfit());
                dhm.setDevicesSalary(dhm.getDevicesSalary() + data.getDevicesSalary());
                dhm.setRepairsCount(dhm.getRepairsCount() + data.getRepairsCount());
                dhm.setRepairsRevenue(dhm.getRepairsRevenue() + data.getRepairsRevenue());
                dhm.setRepairsPurchase(dhm.getRepairsPurchase() + data.getRepairsPurchase());
                dhm.setRepairsProfit(dhm.getRepairsProfit() + data.getRepairsProfit());
                dhm.setRepairsSalary(dhm.getRepairsSalary() + data.getRepairsSalary());
                dhm.setExpenses(dhm.getExpenses() + data.getExpenses());
                dhm.setCash(dhm.getCash() + data.getCash());
                dhm.setCashless(dhm.getCashless() + data.getCashless());
                dhm.setSalary(dhm.getSalary() + data.getSalary());
                dhm.setAllRevenue(dhm.getAllRevenue() + data.getAllRevenue());
            });
            dhm.setEmployee(dataList.get(0).getEmployee());
            dhm.setDate(startAndEndDateTimeListHalfMonth(dataList.get(0).getDateOfData()).get(0));
        }
        return dhm;
    }

    private DataHalfMonth createEmptyDataFullMonth() {
        DataHalfMonth dataHalfMonth = new DataHalfMonth();
        dataHalfMonth.setCountDay(0L);
        dataHalfMonth.setAccessoriesCount(0);
        dataHalfMonth.setAccessoriesRevenue(0);
        dataHalfMonth.setAccessoriesSalary(0d);
        dataHalfMonth.setBonus(0);
        dataHalfMonth.setMarketingMargin(0);
        dataHalfMonth.setMarketingMarginProfit(0d);
        dataHalfMonth.setMarketingMarginSalary(0d);
        dataHalfMonth.setSimCount(0);
        dataHalfMonth.setSimRevenue(0);
        dataHalfMonth.setSimSalary(0d);
        dataHalfMonth.setDevicesCount(0);
        dataHalfMonth.setDevicesRevenue(0);
        dataHalfMonth.setDevicesPurchase(0);
        dataHalfMonth.setDevicesProfit(0);
        dataHalfMonth.setDevicesSalary(0d);
        dataHalfMonth.setRepairsCount(0);
        dataHalfMonth.setRepairsRevenue(0);
        dataHalfMonth.setRepairsPurchase(0);
        dataHalfMonth.setRepairsProfit(0);
        dataHalfMonth.setRepairsSalary(0d);
        dataHalfMonth.setExpenses(0);
        dataHalfMonth.setCash(0);
        dataHalfMonth.setCashless(0);
        dataHalfMonth.setSalary(0d);
        dataHalfMonth.setAllRevenue(0d);
        return dataHalfMonth;
    }
}
