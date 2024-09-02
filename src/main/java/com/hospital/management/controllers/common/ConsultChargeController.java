package com.hospital.management.controllers.common;

import com.hospital.management.entities.commom.ConsultCharge;
import com.hospital.management.service.ConsultChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consult-charge")
public class ConsultChargeController {

    @Autowired
    ConsultChargeService consultChargeService;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<List<ConsultCharge>> consultChargeDataList() {
        List<ConsultCharge> consultChargesList = consultChargeService.consultChargeList();
        return  ResponseEntity.ok(consultChargesList);

    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/saveConsultCharge")
    public ResponseEntity<ConsultCharge> saveConsultCharge(@RequestBody @Validated ConsultCharge consultCharge){
        consultChargeService.saveConsultCharge(consultCharge);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping("/update/{consultId}")
    public ResponseEntity<ConsultCharge> updateConsultCharge(@RequestBody @Validated ConsultCharge consultCharge,@PathVariable("consultId") Long consultId){
        consultChargeService.updateConsultCharge(consultCharge,consultId);
        return new ResponseEntity<>(HttpStatus.OK);
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
