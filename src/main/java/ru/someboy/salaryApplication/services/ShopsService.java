package ru.someboy.salaryApplication.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.someboy.salaryApplication.models.Shop;
import ru.someboy.salaryApplication.repositories.ShopsRepository;

import java.time.LocalDate;
import java.util.List;

import static ru.someboy.salaryApplication.util.Methods.getPresentOptional;

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

    @Transactional
    public void save(Shop shop) {
        shopsRepository.save(enrichShop(shop, false, null));
    }

    @Transactional
    public void update(Shop updateShop, Integer id) {
        shopsRepository.save(enrichShop(updateShop, true, id));
    }

    @Transactional
    public void delete(Integer id) {
        shopsRepository.deleteById(id);
    }

    private Shop enrichShop(Shop shop, boolean isUpdate, Integer id) {
        if (isUpdate) {
            Shop updateShop = (Shop) getPresentOptional(shopsRepository.findById(id));
            if (shop.getName() != null) updateShop.setName(shop.getName());
            if (shop.getRental() != null) updateShop.setRental(shop.getRental());
            if (shop.getAccessoriesPercent() != null) updateShop.setAccessoriesPercent(shop.getAccessoriesPercent());
            if (shop.getMarketingMarginPercent() != null)
                updateShop.setMarketingMarginPercent(shop.getMarketingMarginPercent());
            if(shop.getSimPercent() != null) updateShop.setSimPercent(shop.getSimPercent());
            if(shop.getDevicesPercent() != null) updateShop.setDevicesPercent(shop.getDevicesPercent());
            if(shop.getIsWork() != null) updateShop.setIsWork(shop.getIsWork());
            if(shop.getEmployees() != null) updateShop.setEmployees(shop.getEmployees());
            return updateShop;
        } else {
            shop.setDateOfRegistration(LocalDate.now());
            return shop;
        }
    }
}
