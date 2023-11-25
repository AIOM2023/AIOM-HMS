package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "common_vacutainer")
public class vacutainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "vacutainer_id")
    private Integer vacutainerId;

    @Column(name= "vacutainer_code")
    private String vacutainerCode;

    @Column(name= "vacutainer_value")
    private String vacutainerValue;

    @Column(name= "vacutainer_description")
    private String vacutainerDescription;

}
