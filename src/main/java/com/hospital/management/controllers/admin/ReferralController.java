package com.hospital.management.controllers.admin;

import com.hospital.management.entities.admin.Referral;
import com.hospital.management.service.ReferralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/referral")
@CrossOrigin(origins = "http://localhost:3000")
public class ReferralController {

    @Autowired
    private ReferralService referralService;

    @Autowired
    @GetMapping
    public ResponseEntity<List<Referral>> getAllReferrals() {
        List<Referral> referrals = referralService.getAllReferrals();
        return ResponseEntity.ok(referrals);
    }

    @GetMapping("/{referralId}")
    public ResponseEntity<Referral> findReferralById(@PathVariable("referralId") Long referralId) {
        Referral referral = referralService.findReferralById(referralId);
        return ResponseEntity.ok(referral);
    }

    @PostMapping("/save")
    public ResponseEntity<Referral> saveReferral(@RequestBody @Validated Referral referral){
        return new ResponseEntity<>(referralService.saveReferral(referral), HttpStatus.CREATED);
    }

    @PutMapping("/update/{referralId}")
    public ResponseEntity<Referral> updateReferral(@RequestBody @Validated Referral referral, @PathVariable("referralId") Long referralId){
        return new ResponseEntity<>(referralService.updateReferral(referral, referralId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{referralId}")
    public ResponseEntity<String> deleteReferralById(@PathVariable("referralId") Long referralId){
        return new ResponseEntity<>(referralService.deleteReferralById(referralId), HttpStatus.OK);
    }
}
