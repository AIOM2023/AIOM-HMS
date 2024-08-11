package com.hospital.management.controllers.common;

import com.hospital.management.entities.commom.Department;
import com.hospital.management.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<List<Department>> departmentList() {
        List<Department> department = departmentService.departmentList();
        return  ResponseEntity.ok(department);

    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/save")
    public ResponseEntity<Department> saveDepartment(@RequestBody @Validated Department department){
        departmentService.saveDepartment(department);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{departmentId}")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<Department> findDepartmentById(@PathVariable("departmentId") Integer departmentId) {
        Department department = departmentService.findDepartmentById(departmentId);
        return ResponseEntity.ok(department);

    }



    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping("/update/{departmentId}")
    public ResponseEntity<Department> updateDepartmentById(@RequestBody @Validated Department department,@PathVariable("departmentId") Integer departmentId){
        departmentService.updateDepartmentById(department,departmentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping("/delete/{departmentId}")
    public ResponseEntity<String> deleteDepartmentById(@PathVariable("departmentId") Integer departmentId) {
        String successMsg = departmentService.deleteDepartmentById(departmentId);
        return new ResponseEntity<>(successMsg, HttpStatus.OK);
    }
}
