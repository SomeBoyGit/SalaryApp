package ru.someboy.salaryApplication.controllers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.someboy.salaryApplication.dto.ShopRequest;
import ru.someboy.salaryApplication.dto.ShopResponse;
import ru.someboy.salaryApplication.models.Shop;
import ru.someboy.salaryApplication.services.ShopsService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shops")
@RequiredArgsConstructor
public class ShopsController {
    private final ShopsService shopsService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<ShopResponse> getShops() {
        return shopsService.findAll().stream().map(this::convertToShopResponse).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ShopResponse getShop(@PathVariable("id") int id) {
        return convertToShopResponse(shopsService.findOne(id));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody ShopRequest shopRequest) {
        shopsService.save(convertToShop(shopRequest));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody ShopRequest shopRequest, @PathVariable("id") int id) {
        shopsService.update(convertToShop(shopRequest), id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        shopsService.delete(id);
        return ResponseEntity.ok(HttpStatus.valueOf("Удалено"));
    }

    private Shop convertToShop(ShopRequest shopRequest) {
        return modelMapper.map(shopRequest, Shop.class);
    }

    private Shop convertToShop(ShopResponse shopResponse) {
        return modelMapper.map(shopResponse, Shop.class);
    }

    private ShopResponse convertToShopResponse(Shop shop) {
        return modelMapper.map(shop, ShopResponse.class);
    }
}
