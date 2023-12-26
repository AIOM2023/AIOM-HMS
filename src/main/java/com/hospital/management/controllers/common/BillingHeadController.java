package com.hospital.management.controllers.common;

import com.hospital.management.entities.commom.BedDetails;
import com.hospital.management.entities.commom.BillingHead;
import com.hospital.management.service.BillingHeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/billing/head")
public class BillingHeadController {

    @Autowired
    BillingHeadService billingHeadService;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<List<BillingHead>> billingHeadList() {
        List<BillingHead> billingHeadListList = billingHeadService.billingHeadList();
        return  ResponseEntity.ok(billingHeadListList);

    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/save")
    public ResponseEntity<BillingHead> saveInsuranceComp(@RequestBody @Validated BillingHead billingHead){
        billingHeadService.save(billingHead);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/update")
    public ResponseEntity<BillingHead> updateAuthorization(@RequestBody @Validated BillingHead billingHead){
        billingHeadService.save(billingHead);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
