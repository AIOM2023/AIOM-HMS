package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name= "designation")
public class Designation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "designation_id")
    private Integer designationId;

    @Column(name="designation")
    private String designation;

    @Column(name="description")
    private String description;

    @Column(name="created_date")
    private OffsetDateTime createdDate;

    @Column(name="created_by")
    private String  createdBy;

    @Column(name="modified_date")
    private OffsetDateTime modifiedDate;

    @Column(name="modified_by")
    private String  modifiedBy;

    @Column(name= "status")
    private Integer status;

    @PrePersist
    @PreUpdate
    public void applyUppercase() {
        this.designation = this.designation != null ? this.designation.toUpperCase() : null;
    }

}