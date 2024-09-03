package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name= "nurse_station")
public class NurseStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "nurse_station_id")
    private Long nurseStationId;

    @Column(name= "nurse_station_code")
    private String nurseStationCode;

    @Column(name= "nurse_station_name")
    private String nurseStationName;

    @Column(name= "description")
    private String nurseStationDescription;

    @Column(name="created_date")
    private OffsetDateTime createdDate;

    @Column(name="created_by")
    private String  createdBy;

    @Column(name="modified_date")
    private OffsetDateTime modifiedDate;

    @Column(name="modified_by")
    private String  modifiedBy;

   // @Column(name= "del_nurse_station")
   // private Integer delNurseStation;

    @Column(name= "status")
    private Integer status;

    //9000865648

}
