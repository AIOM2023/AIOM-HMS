package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "common_religion")
public class Religion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "religion_id")
    private Integer religionId;

    @Column(name= "religion_code")
    private String religionCode;

    @Column(name= "religion_value")
    private String religionValue;

    @Column(name= "religion_description")
    private String religionDescription;
}
