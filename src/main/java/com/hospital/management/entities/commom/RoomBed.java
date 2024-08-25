package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name= "room_bed")
public class RoomBed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "room_bed_id")
    private Long roomBedId;

    @Column(name= "room_group_code")
    private String roomGroupCode;

    @Column(name= "room_group_name")
    private String roomGroupName;

    @Column(name= "room_code")
    private String roomCode;

    @Column(name= "no_of_beds")
    private Integer noOfBeds;

    @Column(name= "extension_no")
    private Integer extensionNo;

    @Column(name= "room_status")
    private String roomStatus;

    @Column(name= "room_block")
    private String roomBlock;

    @Column(name= "room_level")
    private String roomLevel;

    @Column(name= "room_wing")
    private String roomWing;

    @Column(name= "nurse_station_code")
    private String nurseStationCode;

    @Column(name= "nurse_station_name")
    private String nurseStationName;

    private Integer status;

    @Column(name="created_date")
    private OffsetDateTime createdDate;

    @Column(name="created_by")
    private String  createdBy;

    @Column(name="modified_date")
    private OffsetDateTime modifiedDate;

    @Column(name="modified_by")
    private String  modifiedBy;
}
