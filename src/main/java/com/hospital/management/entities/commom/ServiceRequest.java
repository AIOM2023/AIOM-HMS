package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name= "service_request")
public class ServiceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "service_request_id")
    private Long serviceRequestId;

    @Column(name= "service_request_code")
    private String serviceRequestCode;

    @Column(name= "serviceRequestName")
    private String serviceRequestName;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name= "created_date")
    private OffsetDateTime createdDate;

    @Column(name= "created_by")
    private String createdBy;

    @Column(name= "modified_date")
    private OffsetDateTime modifiedDate;

    @Column(name= "modified_by")
    private String modifiedBy;

     @Column(name= "status")
    private Integer status;
}

