package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "common_gender")
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "gender_id")
    private Integer genderId;

    @Column(name= "gender_code")
    private String genderCode;

    @Column(name= "gender_value")
    private String genderValue;

    @Column(name= "gender_description")
    private String genderDescription;

}
