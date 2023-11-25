package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "common_civil_status")
public class CivilStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "civil_status_id")
    private Integer civilStatusId;

    @Column(name= "civil_status_code")
    private String civilStatusCode;

    @Column(name= "civil_status_value")
    private String civilStatusValue;

    @Column(name= "civil_status_description")
    private String civilStatusDescription;
}
