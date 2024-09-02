package com.hospital.management.controllers.common;

import com.hospital.management.entities.commom.Department;
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
    public ResponseEntity<List<Designation>> designationList() {
        List<Designation> designation = designationService.designationList();
        return  ResponseEntity.ok(designation);

    }

    @PostMapping("/save")
    public ResponseEntity<Designation> saveDesignation(@RequestBody @Validated Designation designation){
        designationService.saveDesignation(designation);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping ("/update/{designationId}")
    public ResponseEntity<Designation> updateDesignation(@RequestBody @Validated Designation designation,@PathVariable("designationId") Integer designationId){
        designationService.updateDesignation(designation,designationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/{designationId}")
    public ResponseEntity<Designation> findDesignationById(@PathVariable("designationId") Integer designationId) {
        Designation designation = designationService.findDesignationById(designationId);
        return ResponseEntity.ok(designation);

    }

    @DeleteMapping("/delete/{designationId}")
    public ResponseEntity<String> deleteDesignationById(@PathVariable("designationId") Integer designationId) {
        String successMsg = designationService.deleteDesignationById(designationId);
        return new ResponseEntity<>(successMsg, HttpStatus.OK);
    }
}
