package com.hospital.management.controllers.common;

import com.hospital.management.entities.commom.Branches;
import com.hospital.management.entities.response.BranchesSearchResult;
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
    public ResponseEntity<BranchesSearchResult> branchesList(@RequestParam(name="search") String search,
                                                             @RequestParam(defaultValue = "0") int pageNo,
                                                             @RequestParam(defaultValue = "50") int pageSize,
                                                             @RequestParam(name="sortBy") String sortBy,
                                                             @RequestParam(defaultValue = "ASC") String sortOrder) {
        BranchesSearchResult branchesSearchResult = branchesService.branchesList(search, pageNo, pageSize, sortBy, sortOrder);
        return  ResponseEntity.ok(branchesSearchResult);

    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/saveBranches")
    public ResponseEntity<Branches> saveBranches(@RequestBody @Validated Branches branches){
        branchesService.saveBranches(branches);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping("/updateBranches/{branchId}")
    public ResponseEntity<Branches> updateBranch(@RequestBody @Validated Branches branches,@PathVariable("branchId") Long branchId){
        branchesService.updateBranche(branches,branchId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping("/deleteBranches/{branchId}")
    public ResponseEntity<String> deleteBranchesById(@PathVariable("branchId") Long branchId) {
        return new ResponseEntity<>(branchesService.deleteBranchesById(branchId), HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/{branchId}")
    public ResponseEntity<Branches> findBranchesById(@PathVariable("branchId") Long branchId) {
        Branches branches = branchesService.findBranchesById(branchId);
        return ResponseEntity.ok(branches);
    }
}
