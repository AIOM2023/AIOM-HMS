package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name= "bed_details")
public class BedDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "bed_id")
    private Integer bedId;

    @Column(name= "room_bed_id")
    private String roomBedId;

    @Column(name= "bed_code")
    private String bedCode;

    @Column(name= "bed_status")
    private String bedStatus;

    @Column(name= "is_active")
    private String isActive;

    @Column(name= "room_code")
    private String roomCode;

    @Column(name= "room_group_code")
    private String roomGroupCode;

    @Column(name= "room_group_name")
    private String roomGroupName;

    @Column(name= "admission_no")
    private String admissionNo;

    @Column(name="created_date")
    private OffsetDateTime createdDate;

    @Column(name="created_by")
    private String  createdBy;

    @Column(name="modified_date")
    private OffsetDateTime modifiedDate;

    @Column(name="modified_by")
    private String  modifiedBy;

    @Column(name="created_id")
    private String  createdId;

    @Column(name="modified_id")
    private String  modifiedId;

    @Column(name= "del_bed_details")
    private Integer delBedDetails;

    @Column(name= "in_active")
    private Integer inActive;

}
