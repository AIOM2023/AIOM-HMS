package com.hospital.management.controllers.common;

import com.hospital.management.entities.commom.Designation;
import com.hospital.management.service.DesignationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/designation")
public class DesignationController {
    @Autowired
    DesignationService designationService;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<List<Designation>> systemParamsList() {
        List<Designation> designation = designationService.designationList();
        return  ResponseEntity.ok(designation);

    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/save")
    public ResponseEntity<Designation> saveSystemParameters(@RequestBody @Validated Designation designation){
        designationService.save(designation);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/update")
    public ResponseEntity<Designation> updateAuthorization(@RequestBody @Validated Designation designation){
        designationService.update(designation);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
