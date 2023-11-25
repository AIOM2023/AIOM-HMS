package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "common_admsn_purpose")
public class AdmsnPurpose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "admsn_purpose_id")
    private Integer admsnId;

    @Column(name= "admsn_purpose_code")
    private String admsnCode;

    @Column(name= "admsn_purpose_value")
    private String admsnValue;

    @Column(name= "admsn_purpose_description")
    private String admsnDescription;
}
