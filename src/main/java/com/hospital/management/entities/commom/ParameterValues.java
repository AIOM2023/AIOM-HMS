package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "parameter_values")
public class ParameterValues {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "parameter_id")
    private Integer parameterId;

    @Column(name= "lab_parameter_id")
    private String labParameterId;

    @Column(name= "gender")
    private Integer gender;

    @Column(name= "min_age")
    private Integer minAge;

    @Column(name= "min_uom")
    private String minUom;

    @Column(name= "max_age")
    private Integer maxAge;

    @Column(name= "max_uom")
    private String maxUom;

    @Column(name= "descr")
    private String descr;

    @Column(name= "symbol")
    private Integer symbol;

    @Column(name= "min_range")
    private String minRange;

    @Column(name= "max_range")
    private String maxRange;

    @Column(name= "units")
    private Integer units;

    @Column(name= "normal_range")
    private String normalRange;

    @Column(name= "in_active")
    private Integer inActive;
}
