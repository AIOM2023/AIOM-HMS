package com.hospital.management.controllers.common;

import com.hospital.management.entities.commom.ChangeAdmissionDetails;
import com.hospital.management.service.ChangeAdmissionDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/change/admission")
public class ChangeAdmissionDetailsController {

    @Autowired
    ChangeAdmissionDetailsService changeAdmissionDetailsService;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<List<ChangeAdmissionDetails>> ChangeAdmissionDetailsDataList() {
        List<ChangeAdmissionDetails> ChangeAdmissionDetailsList = changeAdmissionDetailsService.changeAdmissionDetailsList();
        return  ResponseEntity.ok(ChangeAdmissionDetailsList);

    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/save")
    public ResponseEntity<ChangeAdmissionDetails> saveInsuranceComp(@RequestBody @Validated ChangeAdmissionDetails changeAdmissionDetails){
        changeAdmissionDetailsService.save(changeAdmissionDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/update")
    public ResponseEntity<ChangeAdmissionDetails> updateAuthorization(@RequestBody @Validated ChangeAdmissionDetails changeAdmissionDetails){
        changeAdmissionDetailsService.save(changeAdmissionDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
