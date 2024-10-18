package com.hospital.management.controllers.common;

import com.hospital.management.entities.commom.ConsultCharge;
import com.hospital.management.entities.response.ConsultChargesSearchResult;
import com.hospital.management.service.ConsultChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consult-charge")
@CrossOrigin(origins = "http://localhost:8080")
public class ConsultChargeController {

    @Autowired
    ConsultChargeService consultChargeService;

    @GetMapping
    public ResponseEntity<ConsultChargesSearchResult> getAllConsultCharges(
            @RequestParam(name="search", required = false) String search,
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "50") int pageSize,
            @RequestParam(name="sortBy", required = false) String sortBy,
            @RequestParam(defaultValue = "DESC") String sortOrder) {
        ConsultChargesSearchResult consultChargesSearchResult = consultChargeService.getAllConsultCharges(search, pageNo, pageSize, sortBy, sortOrder);
        return  ResponseEntity.ok(consultChargesSearchResult);

    }

    @PostMapping("/save")
    public ResponseEntity<ConsultCharge> saveConsultCharge(@RequestBody @Validated ConsultCharge consultCharge){
        ConsultCharge savedConsultCharge = consultChargeService.saveConsultCharge(consultCharge);
        return new ResponseEntity<>(savedConsultCharge, HttpStatus.CREATED);
    }


    @PutMapping("/update/{consultId}")
    public ResponseEntity<ConsultCharge> updateConsultCharge(@RequestBody @Validated ConsultCharge consultCharge,@PathVariable("consultId") Long consultId){
        ConsultCharge updatedConsultCharge = consultChargeService.updateConsultCharge(consultCharge,consultId);
        return new ResponseEntity<>(updatedConsultCharge, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{consultId}")
    public ResponseEntity<String> deleteConsultChargeById(@PathVariable("consultId") Long consultId) {
        return new ResponseEntity<>(consultChargeService.deleteConsultChargeById(consultId), HttpStatus.OK);
    }

    @GetMapping("/{consultId}")
    public ResponseEntity<ConsultCharge> findConsultChargeById(@PathVariable("consultId") Long consultId) {
        ConsultCharge consultCharge = consultChargeService.findConsultChargeById(consultId);
        return ResponseEntity.ok(consultCharge);
    }
}
