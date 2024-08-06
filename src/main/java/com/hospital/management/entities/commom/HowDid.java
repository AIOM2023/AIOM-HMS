package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name= "how_did")
public class HowDid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "how_did_id")
    private Integer howDidId;

    @Column(name= "how_did_name")
    private String howDidName;

    @Column(name= "how_did_desc")
    private String howDidDesc;

    @Column(name= "status")
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
