package com.hospital.management.entities;


import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name= "master_country")
public class Country {

    @Id
    @Column(name= " COUNTRY_ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private  Integer countryid;

    @Column(name="COUNTRY_NAME")
    private  String countryname;

    @Column(name="STATUS")
    private String status;


}
