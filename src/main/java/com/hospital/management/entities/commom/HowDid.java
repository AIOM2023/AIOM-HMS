package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "how_did")
public class HowDid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "how_did_id")
    private Integer howDidId;

    @Column(name= "how_did_name")
    private String howDidName;

    @Column(name= "how_did_desc")
    private String howDidDesc;

    @Column(name= "in_active")
    private Integer inActive;
}
