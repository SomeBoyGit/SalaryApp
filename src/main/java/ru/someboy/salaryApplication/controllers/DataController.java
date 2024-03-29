package ru.someboy.salaryApplication.controllers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.someboy.salaryApplication.dto.DataRequest;
import ru.someboy.salaryApplication.dto.DataResponse;
import ru.someboy.salaryApplication.models.Data;
import ru.someboy.salaryApplication.services.DataService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/data")
@RequiredArgsConstructor
public class DataController {
    private final DataService dataService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<DataResponse> getAllData() {
        return dataService.findAll().stream().map(this::convertToDataResponse).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public DataResponse getData(@PathVariable("id") long id) {
        return convertToDataResponse(dataService.findOne(id));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody DataRequest dataRequest) {
        dataService.save(convertToData(dataRequest));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody DataRequest dataRequest, @PathVariable("id") long id) {
        dataService.update(convertToData(dataRequest), id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
        dataService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Data convertToData(DataRequest dataRequest) {
        return modelMapper.map(dataRequest, Data.class);
    }

    private Data convertToData(DataResponse dataResponse) {
        return modelMapper.map(dataResponse, Data.class);
    }

    private DataResponse convertToDataResponse(Data data) {
        return modelMapper.map(data, DataResponse.class);
    }
}
