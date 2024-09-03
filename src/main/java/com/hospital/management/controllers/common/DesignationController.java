package com.hospital.management.controllers.common;

import com.hospital.management.entities.commom.Department;
import com.hospital.management.entities.commom.Designation;
import com.hospital.management.entities.response.DesignationSearchResult;
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
    public ResponseEntity<DesignationSearchResult> designationList(@RequestParam(name="search") String search,
                                                                         @RequestParam(defaultValue = "0") int pageNo,
                                                                         @RequestParam(defaultValue = "50") int pageSize,
                                                                         @RequestParam(name="sortBy") String sortBy,
                                                                         @RequestParam(defaultValue = "ASC") String sortOrder ) {
        DesignationSearchResult designationSearchResult = designationService.designationList(search, pageNo, pageSize, sortBy, sortOrder);
        return  ResponseEntity.ok(designationSearchResult);

    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/save")
    public ResponseEntity<Designation> saveDesignation(@RequestBody @Validated Designation designation){
        designationService.saveDesignation(designation);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping ("/update/{designationId}")
    public ResponseEntity<Designation> updateDesignation(@RequestBody @Validated Designation designation,@PathVariable("designationId") Long designationId){
        designationService.updateDesignation(designation,designationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/{designationId}")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<Designation> findDesignationById(@PathVariable("designationId") Long designationId) {
        Designation designation = designationService.findDesignationById(designationId);
        return ResponseEntity.ok(designation);

    }

    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping("/delete/{designationId}")
    public ResponseEntity<String> deleteDesignationById(@PathVariable("designationId") Long designationId) {
        String successMsg = designationService.deleteDesignationById(designationId);
        return new ResponseEntity<>(successMsg, HttpStatus.OK);
    }
}
