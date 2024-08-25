package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name= "specialization_master")
public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "specialization_id")
    private Long specializationId;

    @Column(name= "specialization_code")
    private String specializationCode;

    @Column(name= "specialization_val")
    private String specializationVal;

    @Column(name= "specialization_desc")
    private String specializationDesc;

    private Integer status;

    @Column(name= "created_date")
    private OffsetDateTime createdDate;

    @Column(name= "created_by")
    private String createdBy;

    @Column(name= "modified_date")
    private OffsetDateTime modified_date;

    @Column(name= "modified_by")
    private String modified_by;
}



