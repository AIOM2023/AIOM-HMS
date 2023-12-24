package com.hospital.management.controllers.common;

import com.hospital.management.entities.commom.RoomGroup;
import com.hospital.management.service.RoomGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room/bed")
public class RoomBedController {

    @Autowired
    RoomGroupService roomGroupService;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<List<RoomGroup>> tariffList() {
        List<RoomGroup> tariffList = roomGroupService.roomGroupList();
        return  ResponseEntity.ok(tariffList);

    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/save")
    public ResponseEntity<RoomGroup> saveInsuranceComp(@RequestBody @Validated RoomGroup roomGroup){
        roomGroupService.save(roomGroup);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/update")
    public ResponseEntity<RoomGroup> updateAuthorization(@RequestBody @Validated RoomGroup roomGroup){
        roomGroupService.update(roomGroup);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
