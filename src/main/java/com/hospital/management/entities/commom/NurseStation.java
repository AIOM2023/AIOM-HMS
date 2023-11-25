package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "common_nurse_station")
public class NurseStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "nurse_station_id")
    private Integer nurseStationId;

    @Column(name= "nurse_station_code")
    private String nurseStationCode;

    @Column(name= "nurse_station_value")
    private String nurseStationValue;

    @Column(name= "nurse_station_description")
    private String nurseStationDescription;

}
