package ru.someboy.salaryApplication.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.someboy.salaryApplication.models.*;
import ru.someboy.salaryApplication.repositories.DataRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static ru.someboy.salaryApplication.util.Methods.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DataService {
    private final DataRepository dataRepository;
    private final EmployeesService employeesService;
    private final ShopsService shopsService;
    private final DataFullMonthService dataFullMonthService;
    private final DataHalfMonthService dataHalfMonthService;

    public List<Data> findAll() {
        return dataRepository.findAll();
    }

    public Data findOne(Long id) {
        return dataRepository.findById(id).orElse(null);
    }

    public Optional<Data> findDataByDateOfDataAndEmployee(LocalDateTime dateOfData, Employee employee) {
        return dataRepository.findDataByDateOfDataAndEmployee(dateOfData, employee);
    }

    public List<Data> getDataBetweenDates(LocalDateTime dateOfData, Employee employee) {
        return dataRepository.findAllDataByDateOfDataBetweenAndEmployee(
                startAndEndDateTimeList(dateOfData).get(0), startAndEndDateTimeList(dateOfData).get(1),
                employee);
    }

    public List<Data> getDataBetweenDatesHalfMonth(LocalDateTime dateOfData, Employee employee) {
        return dataRepository.findAllDataByDateOfDataBetweenAndEmployee(
                startAndEndDateTimeListHalfMonth(dateOfData).get(0), startAndEndDateTimeListHalfMonth(dateOfData).get(1),
                employee);
    }

    @Transactional
    public void save(Data data) {
        if (data.getDateOfData() == null) {
            dateTimeToStartDayPlusMin(LocalDateTime.now());
        }
//        Employee employee = employeesService.findOne(2);
//        data.setEmployee(employee);
        Optional<Data> optionalData = findDataByDateOfDataAndEmployee(data.getDateOfData(), data.getEmployee());
        if (optionalData.isPresent()) {
            update(data, optionalData.get().getId());
        } else {
            enrichData(data, false, null);
            dataRepository.saveAndFlush(data);
            dataHalfMonthService.save(getDataBetweenDatesHalfMonth(data.getDateOfData(), data.getEmployee()));
            dataFullMonthService.save(getDataBetweenDates(data.getDateOfData(), data.getEmployee()));
        }
    }

    @Transactional
    public void update(Data updateData, Long id) {
        enrichData(updateData, true, id);
        dataRepository.save(updateData);
        dataHalfMonthService.save(getDataBetweenDatesHalfMonth(updateData.getDateOfData(), updateData.getEmployee()));
        dataFullMonthService.save(getDataBetweenDates(updateData.getDateOfData(), updateData.getEmployee()));

    }

    @Transactional
    public void delete(Long id) {
        Optional<Data> dataOptional = dataRepository.findById(id);
        if (dataOptional.isPresent()) {
            Data data = dataOptional.get();
            DataFullMonth dataFullMonth = (DataFullMonth) getPresentOptional(
                    dataFullMonthService.findDFMByDateAndEmployee(startAndEndDateTimeList(data.getDateOfData()).get(0), data.getEmployee()));
            dataRepository.deleteById(id);
            if (dataFullMonth.getCountDay() == 1) {
                dataFullMonthService.delete(dataFullMonth.getId());
            } else {
                dataFullMonthService.save(getDataBetweenDates(data.getDateOfData(), data.getEmployee()));
            }
            DataHalfMonth dataHalfMonth = (DataHalfMonth) getPresentOptional(
                    dataHalfMonthService.findDHMByDateAndEmployee(startAndEndDateTimeListHalfMonth(data.getDateOfData()).get(0), data.getEmployee()));
            if(dataFullMonth.getCountDay() == 1) {
                dataHalfMonthService.delete(dataHalfMonth.getId());
            }
            else {
                dataHalfMonthService.save(getDataBetweenDatesHalfMonth(data.getDateOfData(), data.getEmployee()));
            }
        }
    }

    private void enrichData(Data data, boolean isUpdate, Long id) {
        if (data.getDateOfData() == null) {
            dateTimeToStartDayPlusMin(LocalDateTime.now());
        } else {
            data.setDateOfData(dateTimeToStartDayPlusMin(data.getDateOfData()));
        }
        if (isUpdate) {
            Data updateData = (Data) getPresentOptional(dataRepository.findById(id));
            data.setDateOfData(updateData.getDateOfData());
            data.setEmployee(updateData.getEmployee());
            data.setId(id);
        }
        int repairPercent = 50;// TODO времянка

        Shop shop = data.getShop();
//        Shop shop = shopsService.findOne(1);
//        data.setShop(shop);
        data.setDateOfEntry(LocalDateTime.now());
        data.setAccessoriesSalary(data.getAccessoriesRevenue() * shop.getAccessoriesPercent() / 100.0);
        data.setBonus(data.getAccessoriesRevenue() / 3000 * 100);
        data.setMarketingMarginProfit(data.getMarketingMargin() * shop.getMarketingMarginPercent() / 100.0);
        data.setMarketingMarginSalary(data.getMarketingMargin() - data.getMarketingMarginProfit());
        data.setSimSalary(data.getSimRevenue() * shop.getSimPercent() / 100.0);
        data.setDevicesProfit(data.getDevicesRevenue() - data.getDevicesPurchase());
        data.setDevicesSalary(data.getDevicesProfit() * shop.getDevicesPercent() / 100.0);
        data.setRepairsProfit(data.getRepairsRevenue() - data.getRepairsPurchase());
        data.setRepairsSalary(data.getRepairsProfit() * repairPercent / 100.0);
        data.setSalary(data.getAccessoriesSalary() + data.getMarketingMarginSalary() +
                data.getBonus() + data.getSimSalary() + data.getDevicesSalary() + data.getRepairsSalary());
    }
}
