package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "common_discharge")
public class Discharge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "discharge_id")
    private Integer dischargeId;

    @Column(name= "discharge_code")
    private String dischargeCode;

    @Column(name= "discharge_value")
    private String dischargeValue;

    @Column(name= "discharge_description")
    private String dischargeDescription;

}
