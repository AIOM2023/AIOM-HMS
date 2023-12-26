package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;
@Data
@Entity
@Table(name= "service_category")
public class ServiceCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "service_group_id")
    private Integer serviceGroupId;

    @Column(name= "department_code")
    private Integer departmentCode;

    @Column(name= "service_group_code")
    private String serviceGroupCode;

    @Column(name= "service_group_name")
    private String serviceGroupName;

    @Column(name= "service_group_desc")
    private String serviceGroupDesc;

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

    @Column(name= "del_service_group")
    private Integer delServiceGroup;

    @Column(name= "in_active")
    private Integer inActive;
}
