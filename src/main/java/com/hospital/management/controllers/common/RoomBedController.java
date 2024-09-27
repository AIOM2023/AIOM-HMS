package com.hospital.management.controllers.common;

import com.hospital.management.entities.commom.RoomBed;
import com.hospital.management.entities.response.RoomBedSearchResult;
import com.hospital.management.service.RoomBedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room-bed")
public class RoomBedController {

    @Autowired
    private RoomBedService roomBedService;

    @GetMapping
    public ResponseEntity<RoomBedSearchResult> getAllRoomBeds(
            @RequestParam(name="search", required = false) String search,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "50") int pageSize,
            @RequestParam(name="sortBy", required = false) String sortBy,
            @RequestParam(defaultValue = "DESC") String sortOrder) {
        RoomBedSearchResult roomBedSearchResult = roomBedService.getAllRoomBeds(search, pageNo, pageSize, sortBy, sortOrder);
        return  ResponseEntity.ok(roomBedSearchResult);
    }

    @GetMapping("/{roomBedId}")
    public ResponseEntity<RoomBed> getRoomBedById(@PathVariable("roomBedId") Long roomBedId) {
        RoomBed roomBed = roomBedService.findRoomBedById(roomBedId);
        return ResponseEntity.ok(roomBed);

    }

    @PostMapping("/save")
    public ResponseEntity<RoomBed> saveRoomBed(@RequestBody @Validated RoomBed roomBed){
        RoomBed createdRoomBed = roomBedService.saveRoomBed(roomBed);
        return new ResponseEntity<>(createdRoomBed, HttpStatus.OK);
    }


    @PutMapping("/update/{roomBedId}")
    public ResponseEntity<RoomBed> updateRoomGroup(@RequestBody @Validated RoomBed roomBed, @PathVariable ("roomBedId") Long roomBedId){
        RoomBed updatedRoomBed = roomBedService.updateRoomBed(roomBed, roomBedId);
        return new ResponseEntity<>(updatedRoomBed, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{roomBedId}")
    public ResponseEntity<String> deleteRoomGroupById(@PathVariable("roomBedId") Long roomBedId){
        String successMsg = roomBedService.deleteRoomBedById(roomBedId);
        return new ResponseEntity<>(successMsg, HttpStatus.OK);
    }
}
