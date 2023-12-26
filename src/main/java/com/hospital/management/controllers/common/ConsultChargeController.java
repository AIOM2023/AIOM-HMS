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
@RequestMapping("/consult/charge")
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
    @PostMapping("/save")
    public ResponseEntity<ConsultCharge> saveInsuranceComp(@RequestBody @Validated ConsultCharge consultCharge){
        consultChargeService.save(consultCharge);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/update")
    public ResponseEntity<ConsultCharge> updateAuthorization(@RequestBody @Validated ConsultCharge consultCharge){
        consultChargeService.save(consultCharge);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
