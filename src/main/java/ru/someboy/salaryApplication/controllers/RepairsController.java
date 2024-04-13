package ru.someboy.salaryApplication.controllers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.someboy.salaryApplication.dto.RepairAcceptance;
import ru.someboy.salaryApplication.dto.RepairIssue;
import ru.someboy.salaryApplication.dto.RepairResponse;
import ru.someboy.salaryApplication.models.Repair;
import ru.someboy.salaryApplication.services.EmployeesService;
import ru.someboy.salaryApplication.services.RepairsService;
import ru.someboy.salaryApplication.services.RepairsService2;
import ru.someboy.salaryApplication.services.ShopsService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/repairs")
@RequiredArgsConstructor
public class RepairsController {
//    private final RepairsService repairsService; TODO
    private final RepairsService2 repairsService;
    private final EmployeesService employeesService;
    private final ShopsService shopsService;
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
    public ResponseEntity<HttpStatus> create(@RequestBody RepairAcceptance repairAcceptance) {
        repairsService.save(convertToRepair(repairAcceptance));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/done/{id}")
    public ResponseEntity<HttpStatus> updateDone(@PathVariable("id") long id) {
        repairsService.updateDoneRepair(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/cancel_done/{id}")
    public ResponseEntity<HttpStatus> updateCancelDone(@PathVariable("id") long id) {
        repairsService.updateCancelDoneRepair(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/issue/{id}")
    public ResponseEntity<HttpStatus> updateIssue(@RequestBody RepairIssue repairIssue, @PathVariable("id") long id) {
        repairsService.updateIssueRepair(convertToRepair(repairIssue), id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/cancel_issue/{id}")
    public ResponseEntity<HttpStatus> updateCancelIssue(@PathVariable("id") long id) {
        repairsService.cancelledIssue(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
        repairsService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Repair convertToRepair(RepairAcceptance repairAcceptance) {
        Repair repair = modelMapper.map(repairAcceptance, Repair.class);
        repair.setSeller(employeesService.findOne(repairAcceptance.getSellerIndex()));
        repair.setShop(shopsService.findOne(repairAcceptance.getShopIndex()));
        return repair;
    }

    private Repair convertToRepair(RepairIssue repairIssue) {
        Repair repair = modelMapper.map(repairIssue, Repair.class);
        repair.setEmployee(employeesService.findOne(repairIssue.getEmployeeIndex()));
        repair.setMaster(employeesService.findOne(repairIssue.getMasterIndex()));
        return repair;
    }

    private RepairResponse convertToRepairResponse(Repair repair) {
        return modelMapper.map(repair, RepairResponse.class);
    }
}
