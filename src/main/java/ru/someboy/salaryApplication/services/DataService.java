package ru.someboy.salaryApplication.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.someboy.salaryApplication.models.Data;
import ru.someboy.salaryApplication.models.Employee;
import ru.someboy.salaryApplication.models.Shop;
import ru.someboy.salaryApplication.repositories.DataRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static ru.someboy.salaryApplication.util.Methods.getPresentOptional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DataService {
    private final DataRepository dataRepository;
    private final EmployeesService employeesService;
    private final ShopsService shopsService;

    public List<Data> findAll() {
        return dataRepository.findAll();
    }

    public Data findOne(Long id) {
        return dataRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Data data) {
        enrichData(data, false, null);
        dataRepository.save(data);
    }

    @Transactional
    public void update(Data updateData, Long id) {
        enrichData(updateData, true, id);
        dataRepository.save(updateData);
    }

    @Transactional
    public void delete(Long id) {
        dataRepository.deleteById(id);
    }

    private void enrichData(Data data, boolean isUpdate, Long id) {
        Shop shop;
        Employee employee;
        if(isUpdate) {
            Data updateData = (Data) getPresentOptional(dataRepository.findById(id));
            data.setDateOfData(updateData.getDateOfData());
            employee = updateData.getEmployee();
            shop = updateData.getShop();
            data.setId(id);
        } else {
            shop = shopsService.findOne(1);
            employee = employeesService.findOne(1);
        }
        int repairPercent = 50;

        data.setEmployee(employee);
        data.setShop(shop);
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
