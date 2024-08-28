package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name= "service_group")
public class ServiceGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "service_group_id")
    private Long serviceGroupId;

    @Column(name= "service_group_name")
    private String serviceGroupName;

    @Column(name= "service_group_code")
    private String serviceGroupCode;

    private String department;

    private String description;

    private Integer status;

    @Column(name="created_date")
    private OffsetDateTime createdDate;

    @Column(name="created_by")
    private String  createdBy;

    @Column(name="modified_date")
    private OffsetDateTime modifiedDate;

    @Column(name="modified_by")
    private String  modifiedBy;

}
