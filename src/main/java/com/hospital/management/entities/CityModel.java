package com.hospital.management.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name= "master_city")
public class CityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "CITY_ID")
    private  Integer cityid;

    @Column(name="CITY_NAME")
    private  String cityname;

    @Column(name= "COUNTRY_ID")
    private  Integer countryid;

    @Column(name= "STATE_ID")
    private  Integer stateid;
}
