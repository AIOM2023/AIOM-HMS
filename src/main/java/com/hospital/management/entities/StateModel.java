package com.hospital.management.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name= "master_state")
public class StateModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "STATE_ID")
    private  Integer stateid;

    @Column(name="STATE_NAME")
    private  String statename;

    @Column(name="COUNTRY_ID")
    private  Integer countryid;

   /* @ManyToOne
    @JoinColumn(name="country_id")
    private CountryModel country;*/



}
