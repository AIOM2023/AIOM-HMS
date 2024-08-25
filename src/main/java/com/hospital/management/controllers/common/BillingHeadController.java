package com.hospital.management.controllers.common;

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
@CrossOrigin(origins = "http://localhost:8080")
public class BillingHeadController {

    @Autowired
    BillingHeadService billingHeadService;

    @GetMapping
    public ResponseEntity<List<BillingHead>> getAllBillingHeads() {
        List<BillingHead> billingHeads = billingHeadService.getAllBillingHeads();
        return ResponseEntity.ok(billingHeads);
    }

    @GetMapping("/{billingHeadId}")
    public ResponseEntity<BillingHead> findBillingHeadById(@PathVariable("billingHeadId") Long billingHeadId) {
        BillingHead billingHead = billingHeadService.findBillingHeadById(billingHeadId);
        return ResponseEntity.ok(billingHead);
    }

    @PostMapping("/save")
    public ResponseEntity<BillingHead> saveBillingHead(@RequestBody @Validated BillingHead billingHead){
        return new ResponseEntity<>(billingHeadService.saveBillingHead(billingHead), HttpStatus.CREATED);
    }

    @PutMapping("/update/{billingHeadId}")
    public ResponseEntity<BillingHead> updateBillingHead(@RequestBody @Validated BillingHead billingHead, @PathVariable("billingHeadId") Long billingHeadId){
        return new ResponseEntity<>(billingHeadService.updateBillingHead(billingHead, billingHeadId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{billingHeadId}")
    public ResponseEntity<String> deleteBillingHeadById(@PathVariable("billingHeadId") Long billingHeadId){
        return new ResponseEntity<>(billingHeadService.deleteBillingHeadById(billingHeadId), HttpStatus.OK);
    }
}
