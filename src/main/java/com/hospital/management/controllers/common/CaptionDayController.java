package com.hospital.management.controllers.common;


import com.hospital.management.entities.commom.CaptionDay;
import com.hospital.management.service.CaptionDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/caption/day")
public class CaptionDayController {

    @Autowired
    CaptionDayService captionDayService;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<List<CaptionDay>> captionDayData() {
        List<CaptionDay> captionDayList = captionDayService.captionDayList();
        return  ResponseEntity.ok(captionDayList);

    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/save")
    public ResponseEntity<CaptionDay> saveInsuranceComp(@RequestBody @Validated CaptionDay captionDay){
        captionDayService.save(captionDay);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/update")
    public ResponseEntity<CaptionDay> updateAuthorization(@RequestBody @Validated CaptionDay captionDay){
        captionDayService.save(captionDay);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
