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
    public ResponseEntity<List<Department>> systemParamsList() {
        List<Department> department = departmentService.departmentList();
        return  ResponseEntity.ok(department);

    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/save")
    public ResponseEntity<Department> saveSystemParameters(@RequestBody @Validated Department department){
        departmentService.save(department);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/update")
    public ResponseEntity<Department> updateAuthorization(@RequestBody @Validated Department department){
        departmentService.update(department);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
