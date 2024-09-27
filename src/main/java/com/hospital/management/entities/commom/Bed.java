package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "bed_details")
public class Bed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "bed_id")
    private Long bedId;

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

    //@ManyToOne
    //@JoinColumn(name="room_bed_id", referencedColumnName = "room_bed_id")
    //private RoomBed roomBed;
}
