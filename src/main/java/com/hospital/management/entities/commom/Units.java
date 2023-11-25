package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "common_units")
public class Units {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "units_id")
    private Integer bloodGroupId;

    @Column(name= "units_code")
    private String unitsCode;

    @Column(name= "units_value")
    private String unitsValue;

    @Column(name= "units_description")
    private String unitsDescription;

}
