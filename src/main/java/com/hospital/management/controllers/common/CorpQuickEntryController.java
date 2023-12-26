package com.hospital.management.controllers.common;

import com.hospital.management.entities.commom.CorpQuickEntry;
import com.hospital.management.service.CorpQuickEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/corp/quick/entry")
public class CorpQuickEntryController {

    @Autowired
    CorpQuickEntryService corpQuickEntryService;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<List<CorpQuickEntry>> corpQuickEntryDataList() {
        List<CorpQuickEntry> corpQuickEntryList = corpQuickEntryService.corpQuickEntryList();
        return  ResponseEntity.ok(corpQuickEntryList);

    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/save")
    public ResponseEntity<CorpQuickEntry> saveCorpQuickEntry(@RequestBody @Validated CorpQuickEntry corpQuickEntry){
        corpQuickEntryService.save(corpQuickEntry);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/update")
    public ResponseEntity<CorpQuickEntry> updateCorpQuickEntry(@RequestBody @Validated CorpQuickEntry corpQuickEntry){
        corpQuickEntryService.save(corpQuickEntry);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
