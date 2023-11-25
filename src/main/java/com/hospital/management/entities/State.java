package com.hospital.management.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@Table(name= "master_state")
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "STATE_ID")
    private  Integer id;

    @Column(name="STATE_NAME")
    private  String stateName;

    @Column(name="COUNTRY_ID")
    private  Integer countryId;

   /* @ManyToOne
    @JoinColumn(name="country_id")
    private CountryModel country;*/



}
