package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name= "common_specialization")
public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "specialization_id")
    private Integer specializationId;

    @Column(name= "specialization_code")
    private String specializationCode;

    @Column(name= "specialization_desc")
    private String specializationDesc;

    @Column(name= "specialization_status")
    private Integer specializationStatus;

    @Column(name= "in_active")
    private Integer inActive;

    @Column(name= "appr_specialization")
    private Integer apprSpecialization;

    @Column(name= "del_specialization")
    private Integer delSpecialization;

    @Column(name= "created_date")
    private OffsetDateTime createdDate;

    @Column(name= "created_by")
    private String createdBy;

    @Column(name= "created_id")
    private String createdId;

    @Column(name= "modified_date")
    private OffsetDateTime modified_date;

    @Column(name= "modified_by")
    private String modified_by;

    @Column(name= "modified_id")
    private String modified_id;

}



