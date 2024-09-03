package com.hospital.management.controllers.admin;

import com.hospital.management.entities.admin.Referral;
import com.hospital.management.entities.response.ReferralSearchResult;
import com.hospital.management.repositary.ReferralRepo;
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
    ReferralRepo referralRepo;


    @GetMapping
    public ResponseEntity<ReferralSearchResult> getAllReferrals(@RequestParam(name="search") String search,
                                                                @RequestParam(defaultValue = "0") int pageNo,
                                                                @RequestParam(defaultValue = "50") int pageSize,
                                                                @RequestParam(name="sortBy") String sortBy,
                                                                @RequestParam(defaultValue = "ASC") String sortOrder ) {
        ReferralSearchResult referralSearchResult = referralService.getAllReferrals(search, pageNo, pageSize, sortBy, sortOrder);
        return ResponseEntity.ok(referralSearchResult);
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
