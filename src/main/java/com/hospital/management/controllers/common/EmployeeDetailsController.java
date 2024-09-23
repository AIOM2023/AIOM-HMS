package com.hospital.management.controllers.common;


import com.hospital.management.entities.commom.EmployeeDetails;
import com.hospital.management.entities.response.EmployeeDetailsSearchResult;
import com.hospital.management.repositary.EmployeeDetailsRepo;
import com.hospital.management.service.EmployeeDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Employee")
public class EmployeeDetailsController {

    @Autowired
    private EmployeeDetailsService employeeDetailsService;

    @Autowired
    EmployeeDetailsRepo employeeDetailsRepo;

    @GetMapping
    public ResponseEntity<EmployeeDetailsSearchResult> getAllEmployeeDetails(
            @RequestParam(name="search") String search,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "50") int pageSize,
            @RequestParam(name="sortBy") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortOrder ) {
        EmployeeDetailsSearchResult employeeDetailsSearchResult = employeeDetailsService.getAllEmployeeDetails(search, pageNo, pageSize, sortBy, sortOrder);
        return ResponseEntity.ok(employeeDetailsSearchResult);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDetails> findEmployeeById(@PathVariable("employeeId") Long employeeId) {
        EmployeeDetails employeeDetails = employeeDetailsService.findEmployeeDetailsById(employeeId);
        return ResponseEntity.ok(employeeDetails);
    }

    @PostMapping("/saveEmployee")
    public ResponseEntity<EmployeeDetails> saveEmployeeDetails(@RequestBody @Validated EmployeeDetails employeeDetails){
        return new ResponseEntity<>(employeeDetailsService.saveEmployeeDetails(employeeDetails), HttpStatus.CREATED);
    }

    @PutMapping("/updateEmployee/{employeeId}")
    public ResponseEntity<EmployeeDetails> updateEmployeeDetails(@RequestBody @Validated EmployeeDetails employeeDetails, @PathVariable("employeeId") Long employeeId){
        return new ResponseEntity<>(employeeDetailsService.updateEmployeeDetails(employeeDetails, employeeId), HttpStatus.OK);
    }

    @DeleteMapping("/deleteEmployee/{employeeId}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable("employeeId") Long employeeId){
        return new ResponseEntity<>(employeeDetailsService.deleteEmployeeDetailsById(employeeId), HttpStatus.OK);
    }
}
