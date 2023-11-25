package com.hospital.management.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name= "master_city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "CITY_ID")
    private  Integer cityId;

    @Column(name="CITY_NAME")
    private  String cityName;

    @Column(name= "COUNTRY_ID")
    private  Integer countryId;

    @Column(name= "STATE_ID")
    private  Integer stateId;
}
