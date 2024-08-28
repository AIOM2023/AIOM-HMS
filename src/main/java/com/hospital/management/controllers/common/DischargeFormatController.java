package com.hospital.management.controllers.common;

import com.hospital.management.entities.commom.DischargeFormat;
import com.hospital.management.entities.commom.DischargeSummary;
import com.hospital.management.service.impl.DischargeFormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discharge/Format")
public class DischargeFormatController {

    @Autowired
    DischargeFormatService dischargeFormatService;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<List<DischargeFormat>> dischargeFormatList() {
        List<DischargeFormat> dischargeFormat = dischargeFormatService.dischargeFormatList();
        return ResponseEntity.ok(dischargeFormat);

    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/saveDischargeFormat")
    public ResponseEntity<DischargeFormat> saveDischargeFormat(@RequestBody @Validated DischargeFormat dischargeFormat) {
        dischargeFormatService.saveDischargeFormat(dischargeFormat);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping("/update/{discFmtId}")
    public ResponseEntity<DischargeSummary> updateDischargeFormat(@RequestBody @Validated DischargeFormat dischargeFormat, @PathVariable("discFmtId") Long discFmtId) {
        dischargeFormatService.updateDischargeFormat(dischargeFormat, discFmtId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping("/delete/{discFmtId}")
    public ResponseEntity<String> deleteDischargeFormatById(@PathVariable("discFmtId") Long discFmtId) {
        return new ResponseEntity<>(dischargeFormatService.deleteDischargeFormatById(discFmtId), HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/{discFmtId}")
    public ResponseEntity<DischargeFormat> findDischargeFormatById(@PathVariable("discFmtId") Long discFmtId) {
        DischargeFormat dischargeFormat = dischargeFormatService.findDischargeFromatById(discFmtId);
        return ResponseEntity.ok(dischargeFormat);
    }

}
