package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name= "ward_charges")
public class WardCharge {

    @Id
    @Column(name="ward_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wardId;

    @Column(name= "ward_code")
    private String wardCode;

    @Column(name= "ward_type")
    private String wardType;

    @Column(name= "normal_charge")
    private Double normalCharge;

    @Column(name= "emergency_charge")
    @Transient
    private Double emergencyCharge;

    @Column(name= "revisit_charge", insertable = false)
    private Double revisitCharge;

    private Integer status;

    @Column(name= "created_date")
    private OffsetDateTime createdDate;

    @Column(name= "created_by")
    private String createdBy;

    @Column(name= "modified_date")
    private OffsetDateTime modifiedDate;

    @Column(name= "modified_by")
    private String modifiedBy;
}
