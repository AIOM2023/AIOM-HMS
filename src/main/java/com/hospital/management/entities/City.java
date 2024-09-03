package com.hospital.management.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name= "master_city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "CITY_ID")
    private  Long cityId;

    @Column(name="CITY_CODE")
    private String cityCode;

    @Column(name="CITY_NAME")
    private String cityName;

    @Column(name= "COUNTRY_NAME")
    private String countryName;

    @Column(name= "STATE_NAME")
    private String stateName;

    @Column(name= "DISTRICT_NAME")
    private String districtName;

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
