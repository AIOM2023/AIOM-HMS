package com.hospital.management.controllers.common;

import com.hospital.management.entities.commom.Branches;
import com.hospital.management.service.BranchesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/branches")
public class BranchesController {

    @Autowired
    BranchesService branchesService;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<List<Branches>> systemParamsList() {
        List<Branches> designation = branchesService.branchesList();
        return  ResponseEntity.ok(designation);

    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/save")
    public ResponseEntity<Branches> saveSystemParameters(@RequestBody @Validated Branches branches){
        branchesService.save(branches);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/update")
    public ResponseEntity<Branches> updateAuthorization(@RequestBody @Validated Branches branches){
        branchesService.update(branches);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
