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
@Table(name= "master_country")
public class CountryModel {

    @Id
    @Column(name= " COUNTRY_ID")
    private  Integer countryid;

    @Column(name="COUNTRY_NAME")
    private  String countryname;


}
