package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "service_request")
public class ServiceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "service_request_id")
    private Long serviceRequestId;

    @Column(name= "service_request_code")
    private String serviceRequestCode;

    @Column(name= "serviceRequestName", unique = true)
    private String serviceRequestName;

    @Column(name= "in_active")
    @Builder.Default
    private Integer status = 0;

    @Column(name= "created_by")
    @Builder.Default
    private String createdBy = "System";

    @Column(name= "created_date")
    @CreationTimestamp
    private OffsetDateTime createdDate;

    @Column(name= "created_id")
    private String createdId;

    @Column(name= "modified_date")
    @UpdateTimestamp
    private OffsetDateTime modifiedDate;

    @Column(name= "modified_by")
    private String modifiedBy;

    @Column(name= "modified_id")
    private String modifiedId;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}

