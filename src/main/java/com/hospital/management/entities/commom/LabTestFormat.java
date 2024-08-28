package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name= "lab_test_format")
public class LabTestFormat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "test_format_id")
    private Long testFormatId;

    @Column(name= "service_group_code")
    private String serviceGroupCode;

    @Column(name= "service_group_name")
    private String serviceGroupName;

    @Column(name= "test_code")
    private String testCode;

    @Column(name= "test_name")
    private String testName;

    @Column(name= "test_format_code")
    private String formatCode;

    @Column(name= "format_name")
    private String formatName;

    @Column(name= "lab_equal_name")
    private String labEqualName;

    @Column(name= "report_title")
    private String reportTitle;

    private String gender;

    @Column(name= "normal_range")
    private Long normalRange;

    private Long dosage;

    private String specimen;

    private String vacutainer;

    @Column(name= "min_time")
    private String minTime;

    @Column(name= "max_time")
    private String maxTime;

    @Column(name= "service_type")
    private String serviceType;

    @Column(name= "billing_head_desc")
    private String billingHeadDesc;

    private Integer status;

    @Column(name= "created_date")
    private OffsetDateTime createdDate;

    @Column(name= "created_by")
    private String createdBy;

    @Column(name= "modified_date")
    private OffsetDateTime modifiedDate;

    @Column(name= "modified_by")
    private String modifiedBy;
}
