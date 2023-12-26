package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name= "lab_parameters")
public class LabParameters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "lab_id")
    private Integer lab_id;

    @Column(name= "lab_group")
    private String lab_group;

    @Column(name= "paramId")
    private String param_id;

    @Column(name= "paramName")
    private String param_name;

    @Column(name= "method")
    private Integer method;

    @Column(name= "short_name")
    private String shortName;

    @Column(name= "normal_ranges")
    private Integer normalRanges;

    @Column(name= "units_checked")
    private Integer unitsChecked;

    @Column(name= "units")
    private Integer units;

    @Column(name= "critical_ranges")
    private Integer criticalRanges;

    @Column(name= "default_ranges")
    private Integer defaultRanges;

    @Column(name= "lab_description")
    private Integer labDescription;

    @Column(name= "editor_desc")
    private String editorDesc;

    @Column(name= "mult_val")
    private Integer multVal;

    @Column(name= "mult_values")
    private String multValues;

    @Column(name= "created_date")
    private OffsetDateTime createdDate;

    @Column(name= "created_by")
    private String createdBy;

    @Column(name= "modified_date")
    private OffsetDateTime modifiedDate;

    @Column(name= "modified_by")
    private String modifiedBy;

    @Column(name= "created_id")
    private String createdId;

    @Column(name= "modified_id")
    private String modifiedId;

    @Column(name= "del_lp")
    private Integer delLp;

    @Column(name= "in_active")
    private Integer inActive;
}
