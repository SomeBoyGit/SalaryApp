package ru.someboy.salaryApplication.controllers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.someboy.salaryApplication.dto.RepairAcceptance;
import ru.someboy.salaryApplication.dto.RepairResponse;
import ru.someboy.salaryApplication.models.Repair;
import ru.someboy.salaryApplication.services.RepairsService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/repairs")
@RequiredArgsConstructor
public class RepairsController {
    private final RepairsService repairsService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<RepairResponse> getRepairs() {
        return repairsService.findAll().stream().map(this::convertToRepairResponse).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public RepairResponse getRepair(@PathVariable("id") long id) {
        return convertToRepairResponse(repairsService.findOne(id));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> repairAcceptance(@RequestBody RepairAcceptance repairAcceptance) {
        repairsService.save(convertToRepair(repairAcceptance));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody RepairAcceptance repairRequest, @PathVariable("id") long id) {
        repairsService.update(convertToRepair(repairRequest), id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
        repairsService.delete(id);
        return ResponseEntity.ok(HttpStatus.valueOf("Удалено"));
    }

    private Repair convertToRepair(RepairAcceptance repairRequest) {
        return modelMapper.map(repairRequest, Repair.class);
    }

    private Repair convertToRepair(RepairResponse repairResponse) {
        return modelMapper.map(repairResponse, Repair.class);
    }

    private RepairResponse convertToRepairResponse(Repair repair) {
        return modelMapper.map(repair, RepairResponse.class);
    }
}
