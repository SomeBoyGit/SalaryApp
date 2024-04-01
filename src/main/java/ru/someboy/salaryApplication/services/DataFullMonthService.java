package ru.someboy.salaryApplication.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.someboy.salaryApplication.models.Data;
import ru.someboy.salaryApplication.models.DataFullMonth;
import ru.someboy.salaryApplication.models.Employee;
import ru.someboy.salaryApplication.repositories.DataFullMonthRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import static ru.someboy.salaryApplication.util.Methods.startAndEndDateTimeList;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DataFullMonthService {
    private final DataFullMonthRepository dfmRepository;
    private final EmployeesService employeesService;
    private final ModelMapper modelMapper;

    public List<DataFullMonth> findAll() {
        return dfmRepository.findAll();
    }

    public DataFullMonth findOne(long id) {
        return dfmRepository.findById(id).orElse(null);
    }

    public Optional<DataFullMonth> findDFMByDateAndEmployee(LocalDateTime date, Employee employee) {
        return dfmRepository.findDataFullMonthByDateAndEmployee(date, employee);
    }

    @Transactional
    public void save(List<Data> dataList) {
        Optional<DataFullMonth> optionalDFM = findDFMByDateAndEmployee(
                startAndEndDateTimeList(dataList.get(0).getDateOfData()).get(0), dataList.get(0).getEmployee());
        if (optionalDFM.isPresent()) {
            update(dataList, optionalDFM.get().getId());
        } else {
            dfmRepository.save(getDataFullMonthFromDataList(dataList));
        }
    }

    @Transactional
    public void update(List<Data> dataList, Long id) {
        DataFullMonth dataFullMonth = getDataFullMonthFromDataList(dataList);
        dataFullMonth.setId(id);
        dfmRepository.save(dataFullMonth);
    }

    @Transactional
    public void delete(long id) {
        dfmRepository.deleteById(id);
    }

    private DataFullMonth getDataFullMonthFromDataList(List<Data> dataList) {
        DataFullMonth dfm = createEmptyDataFullMonth();
        if (!dataList.isEmpty()) {
            AtomicLong count = new AtomicLong(0L);
            dataList.forEach(data -> {
                dfm.setCountDay(count.incrementAndGet());
                dfm.setAccessoriesCount(dfm.getAccessoriesCount() + data.getAccessoriesCount());
                dfm.setAccessoriesRevenue(dfm.getAccessoriesRevenue() + data.getAccessoriesRevenue());
                dfm.setAccessoriesSalary(dfm.getAccessoriesSalary() + data.getAccessoriesSalary());
                dfm.setBonus(dfm.getBonus() + data.getBonus());
                dfm.setMarketingMargin(dfm.getMarketingMargin() + data.getMarketingMargin());
                dfm.setMarketingMarginProfit(dfm.getMarketingMarginProfit() + data.getMarketingMarginProfit());
                dfm.setMarketingMarginSalary(dfm.getMarketingMarginSalary() + data.getMarketingMarginSalary());
                dfm.setSimCount(dfm.getSimCount() + data.getSimCount());
                dfm.setSimRevenue(dfm.getSimRevenue() + data.getSimRevenue());
                dfm.setSimSalary(dfm.getSimSalary() + data.getSimSalary());
                dfm.setDevicesCount(dfm.getDevicesCount() + data.getDevicesCount());
                dfm.setDevicesRevenue(dfm.getDevicesRevenue() + data.getDevicesRevenue());
                dfm.setDevicesPurchase(dfm.getDevicesPurchase() + data.getDevicesPurchase());
                dfm.setDevicesProfit(dfm.getDevicesProfit() + data.getDevicesProfit());
                dfm.setDevicesSalary(dfm.getDevicesSalary() + data.getDevicesSalary());
                dfm.setRepairsCount(dfm.getRepairsCount() + data.getRepairsCount());
                dfm.setRepairsRevenue(dfm.getRepairsRevenue() + data.getRepairsRevenue());
                dfm.setRepairsPurchase(dfm.getRepairsPurchase() + data.getRepairsPurchase());
                dfm.setRepairsProfit(dfm.getDevicesProfit() + data.getDevicesProfit());
                dfm.setRepairsSalary(dfm.getRepairsSalary() + data.getRepairsSalary());
                dfm.setExpenses(dfm.getExpenses() + data.getExpenses());
                dfm.setCash(dfm.getCash() + data.getCash());
                dfm.setCashless(dfm.getCashless() + data.getCashless());
                dfm.setSalary(dfm.getSalary() + data.getSalary());
            });
            dfm.setEmployee(dataList.get(0).getEmployee());
            dfm.setDate(startAndEndDateTimeList(dataList.get(0).getDateOfData()).get(0));
        }
        return dfm;
    }

    private DataFullMonth createEmptyDataFullMonth() {
        DataFullMonth dataFullMonth = new DataFullMonth();
        dataFullMonth.setCountDay(0L);
        dataFullMonth.setAccessoriesCount(0);
        dataFullMonth.setAccessoriesRevenue(0);
        dataFullMonth.setAccessoriesSalary(0d);
        dataFullMonth.setBonus(0);
        dataFullMonth.setMarketingMargin(0);
        dataFullMonth.setMarketingMarginProfit(0d);
        dataFullMonth.setMarketingMarginSalary(0d);
        dataFullMonth.setSimCount(0);
        dataFullMonth.setSimRevenue(0);
        dataFullMonth.setSimSalary(0d);
        dataFullMonth.setDevicesCount(0);
        dataFullMonth.setDevicesRevenue(0);
        dataFullMonth.setDevicesPurchase(0);
        dataFullMonth.setDevicesProfit(0d);
        dataFullMonth.setDevicesSalary(0d);
        dataFullMonth.setRepairsCount(0);
        dataFullMonth.setRepairsRevenue(0);
        dataFullMonth.setRepairsPurchase(0);
        dataFullMonth.setRepairsProfit(0d);
        dataFullMonth.setRepairsSalary(0d);
        dataFullMonth.setExpenses(0);
        dataFullMonth.setCash(0);
        dataFullMonth.setCashless(0);
        dataFullMonth.setSalary(0d);
        return dataFullMonth;
    }
}
