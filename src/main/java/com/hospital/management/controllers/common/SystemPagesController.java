package com.hospital.management.controllers.common;

import com.hospital.management.entities.commom.SystemPages;
import com.hospital.management.service.SystemPagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/pages")
public class SystemPagesController {

    @Autowired
    private SystemPagesService systemPagesService;

    @GetMapping
    public ResponseEntity<List<SystemPages>> systemPagesList() {
        List<SystemPages> systemParams = systemPagesService.getSystemPagesList();
        return  ResponseEntity.ok(systemParams);

    }

    @PostMapping("/save")
    public ResponseEntity<SystemPages> saveSystemParameters(@RequestBody @Validated SystemPages systemPages){
          SystemPages saveSystemPages = systemPagesService.save(systemPages);
          return new ResponseEntity<>(saveSystemPages, HttpStatus.OK);
    }


    @PostMapping("/update")
    public ResponseEntity<SystemPages> updateAuthorization(@RequestBody @Validated SystemPages systemPages){
        SystemPages updateSystemPages = systemPagesService.update(systemPages);
        return new ResponseEntity<>(updateSystemPages,HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<SystemPages> deleteAuthorization(@RequestBody SystemPages systemPages){
        SystemPages deleteSystemPages =systemPagesService.delete(systemPages.getSystemPagesId());
        return new ResponseEntity<>(deleteSystemPages,HttpStatus.OK);
    }
}
