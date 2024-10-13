package com.hospital.management.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name= "master_district")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "district_id")
    private Long districtId;

    @Column(name="district_code")
    private String districtCode;

    @Column(name="district_name")
    private String districtName;

    @Column(name= "state_name")
    private String stateName;

    @Column(name= "country_name")
    private String countryName;

    @Column(name= "status")
    private Integer status;

    @Column(name="created_by")
    private String createdBy;

    @Column(name="created_date")
    private OffsetDateTime createdDate;

    @Column(name="modified_by")
    private String  modifiedBy;

    @Column(name="modified_date")
    private OffsetDateTime modifiedDate;

    @ManyToOne
    @JoinColumn(name = "STATE_ID")
    private State state;
}
