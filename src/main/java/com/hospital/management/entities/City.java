package com.hospital.management.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name= "COUNTRY_ID")
    private String countryId;

    @Column(name= "STATE_ID")
    private String stateId;

    @Column(name= "DISTRICT_ID")
    private String districtId;

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
    @JoinColumn(name = "district_fk_id")
    private District district;
}
