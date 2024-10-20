package com.hospital.management.controllers.common;

import com.hospital.management.entities.commom.WardCharge;
import com.hospital.management.service.WardChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ward")
@CrossOrigin(origins = "http://localhost:8080")
public class WardChargeController {

    @Autowired
    private WardChargeService wardChargeService;

    @GetMapping
    public ResponseEntity<List<WardCharge>> getAllWards() {
        List<WardCharge> wardCharges = wardChargeService.getAllWards();
        return ResponseEntity.ok(wardCharges);
    }

    @GetMapping("/{wardId}")
    public ResponseEntity<WardCharge> findWardById(@PathVariable("wardId") Long wardId) {
        WardCharge wardCharge = wardChargeService.findWardById(wardId);
        return ResponseEntity.ok(wardCharge);
    }

    @PostMapping("/save")
    public ResponseEntity<WardCharge> saveWard(@RequestBody WardCharge wardCharge){

        return new ResponseEntity<>(wardChargeService.saveWard(wardCharge), HttpStatus.CREATED);
    }

    @PutMapping("/update/{wardId}")
    public ResponseEntity<WardCharge> updateWard(@RequestBody @Validated WardCharge wardCharge, @PathVariable("wardId") Long wardId){
        return new ResponseEntity<>(wardChargeService.updateWard(wardCharge, wardId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{wardId}")
    public ResponseEntity<String> deleteWardById(@PathVariable("wardId") Long wardId){
        return new ResponseEntity<>(wardChargeService.deleteWardById(wardId), HttpStatus.OK);
    }
}
