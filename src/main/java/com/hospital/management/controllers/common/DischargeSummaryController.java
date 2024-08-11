package com.hospital.management.controllers.common;

import com.hospital.management.entities.commom.DischargeSummary;
import com.hospital.management.service.DischargeSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discharge/summary")
public class DischargeSummaryController {

    @Autowired
    DischargeSummaryService dischargeSummaryService;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<List<DischargeSummary>> dischargeSummaryList() {
        List<DischargeSummary> dischargeSummary = dischargeSummaryService.dischargeSummaryList();
        return ResponseEntity.ok(dischargeSummary);

    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/saveDischargeSummary")
    public ResponseEntity<DischargeSummary> saveDischargeSummary(@RequestBody @Validated DischargeSummary dischargeSummary) {
        dischargeSummaryService.saveDischargeSummary(dischargeSummary);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping("/update/{dischargeId}")
    public ResponseEntity<DischargeSummary> updateDischargeSummary(@RequestBody @Validated DischargeSummary dischargeSummary, @PathVariable("dischargeId") Long dischargeId) {
        dischargeSummaryService.updateDischargeSummary(dischargeSummary, dischargeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{dischargeId}")
    public ResponseEntity<String> deleteReferralById(@PathVariable("dischargeId") Long dischargeId) {
        return new ResponseEntity<>(dischargeSummaryService.deleteDischargeSummaryById(dischargeId), HttpStatus.OK);
    }

    @GetMapping("/{dischargeId}")
    public ResponseEntity<DischargeSummary> findReferralById(@PathVariable("dischargeId") Long dischargeId) {
        DischargeSummary dischargeSummary = dischargeSummaryService.findDischargeSummaryById(dischargeId);
        return ResponseEntity.ok(dischargeSummary);
    }

}
