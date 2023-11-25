package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

@Data
@Entity
@Table(name= "common_tariff")
public class Tariff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "tariff_id")
    private Integer tariff_id;

    @Column(name= "tariff_code")
    private String tariffCode;

    @Column(name= "tariff")
    private String tariff;

    @Column(name= "tariff_color")
    private String tariffColor;

    @Column(name= "tariff_description")
    private String description;
}
