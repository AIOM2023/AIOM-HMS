package com.hospital.management.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name= "master_country")
public class Country {

    @Id
    @Column(name= "COUNTRY_ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private  Integer countryId;

    @Column(name="COUNTRY_CODE")
    private  String countryCode;

    @Column(name="COUNTRY_NAME")
    private  String countryName;

    @Column(name="STATUS")
    private Integer status;

    @Column(name="created_by")
    private String createdBy;

    @Column(name="created_date")
    private OffsetDateTime createdDate;

    @Column(name="modified_by")
    private String  modifiedBy;

    @Column(name="modified_date")
    private OffsetDateTime modifiedDate;

}
