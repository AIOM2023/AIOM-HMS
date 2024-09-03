package com.hospital.management.controllers.common;

import com.hospital.management.entities.commom.ServiceGroup;
import com.hospital.management.entities.response.ServiceGroupSearchResult;
import com.hospital.management.service.ServiceGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/serviceGroup")
@CrossOrigin(origins = "http://localhost:8080")
public class ServiceGroupController {

    @Autowired
    private ServiceGroupService serviceGroupService;

    @GetMapping
    public ResponseEntity<ServiceGroupSearchResult> getAllServiceGroups(@RequestParam(name="search") String search,
                                                                        @RequestParam(defaultValue = "0") int pageNo,
                                                                        @RequestParam(defaultValue = "50") int pageSize,
                                                                        @RequestParam(name="sortBy") String sortBy,
                                                                        @RequestParam(defaultValue = "ASC") String sortOrder) {
        ServiceGroupSearchResult serviceGroupSearchResult = serviceGroupService.getAllServiceGroups(search, pageNo, pageSize, sortBy, sortOrder);
        return  ResponseEntity.ok(serviceGroupSearchResult);
    }

    @GetMapping("/{serviceGroupId}")
    public ResponseEntity<ServiceGroup> getServiceGroupById(@PathVariable("serviceGroupId") Long serviceGroupId) {
        ServiceGroup serviceGroup = serviceGroupService.findServiceGroupById(serviceGroupId);
        return ResponseEntity.ok(serviceGroup);

    }

    @PostMapping("/save")
    public ResponseEntity<ServiceGroup> saveServiceGroup(@RequestBody @Validated ServiceGroup serviceGroup){
        ServiceGroup createdServiceGroup = serviceGroupService.saveServiceGroup(serviceGroup);
        return new ResponseEntity<>(createdServiceGroup, HttpStatus.OK);
    }


    @PutMapping("/update/{serviceGroupId}")
    public ResponseEntity<ServiceGroup> updateServiceGroup(@RequestBody @Validated ServiceGroup serviceGroup, @PathVariable ("serviceGroupId") Long serviceGroupId){
        ServiceGroup updatedServiceGroup = serviceGroupService.updateServiceGroup(serviceGroup, serviceGroupId);
        return new ResponseEntity<>(updatedServiceGroup, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{serviceGroupId}")
    public ResponseEntity<String> deleteServiceGroupById(@PathVariable("serviceGroupId") Long serviceGroupId){
        String successMsg = serviceGroupService.deleteServiceGroupById(serviceGroupId);
        return new ResponseEntity<>(successMsg, HttpStatus.OK);
    }
}
