package com.hospital.management.controllers.common;

import com.hospital.management.entities.commom.DischargeSummary;
import com.hospital.management.service.DischargeSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discharge/summary")
public class DischargeSummaryController {

    @Autowired
    DischargeSummaryService dischargeSummaryService;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<List<DischargeSummary>> systemParamsList() {
        List<DischargeSummary> dischargeSummary = dischargeSummaryService.dischargeSummaryList();
        return  ResponseEntity.ok(dischargeSummary);

    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/save")
    public ResponseEntity<DischargeSummary> saveSystemParameters(@RequestBody @Validated DischargeSummary dischargeSummary){
        dischargeSummaryService.save(dischargeSummary);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/update")
    public ResponseEntity<DischargeSummary> updateAuthorization(@RequestBody @Validated DischargeSummary dischargeSummary){
        dischargeSummaryService.update(dischargeSummary);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
