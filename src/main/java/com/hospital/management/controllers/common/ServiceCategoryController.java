package com.hospital.management.controllers.common;

import com.hospital.management.entities.commom.ServiceCategory;
import com.hospital.management.service.ServiceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service/category")
public class ServiceCategoryController {

    @Autowired
    ServiceCategoryService serviceCategoryService;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<List<ServiceCategory>> serviceCategoryDataList() {
        List<ServiceCategory> serviceCategoryList = serviceCategoryService.serviceCategoryList();
        return  ResponseEntity.ok(serviceCategoryList);

    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/save")
    public ResponseEntity<ServiceCategory> saveServiceCategory(@RequestBody @Validated ServiceCategory serviceCategory){
        serviceCategoryService.save(serviceCategory);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/update")
    public ResponseEntity<ServiceCategory> updateServiceCategory(@RequestBody @Validated ServiceCategory serviceCategory){
        serviceCategoryService.save(serviceCategory);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
