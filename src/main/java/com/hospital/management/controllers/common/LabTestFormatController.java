package com.hospital.management.controllers.common;

import com.hospital.management.entities.commom.LabTestFormat;
import com.hospital.management.service.LabTestFormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/labTestFormat")
@CrossOrigin(origins = "http://localhost:8080")
public class LabTestFormatController {

    @Autowired
    private LabTestFormatService labTestFormatService;

    @GetMapping
    public ResponseEntity<List<LabTestFormat>> getAllTestFormats() {
        List<LabTestFormat> labTestFormats = labTestFormatService.getAllTestFormats();
        return ResponseEntity.ok(labTestFormats);
    }

    @GetMapping("/{testFormatId}")
    public ResponseEntity<LabTestFormat> findTestFormatById(@PathVariable("testFormatId") Long testFormatId) {
        LabTestFormat labTestFormat = labTestFormatService.findTestFormatById(testFormatId);
        return ResponseEntity.ok(labTestFormat);
    }

    @PostMapping("/save")
    public ResponseEntity<LabTestFormat> saveTestFormat(@RequestBody @Validated LabTestFormat labTestFormat){
        return new ResponseEntity<>(labTestFormatService.saveTestFormat(labTestFormat), HttpStatus.CREATED);
    }

    @PutMapping("/update/{testFormatId}")
    public ResponseEntity<LabTestFormat> updateTestFormat(@RequestBody @Validated LabTestFormat labTestFormat, @PathVariable("testFormatId") Long testFormatId){
        return new ResponseEntity<>(labTestFormatService.updateTestFormat(labTestFormat, testFormatId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{testFormatId}")
    public ResponseEntity<String> deleteTestFormatById(@PathVariable("testFormatId") Long testFormatId){
        return new ResponseEntity<>(labTestFormatService.deleteTestFormatById(testFormatId), HttpStatus.OK);
    }
}
