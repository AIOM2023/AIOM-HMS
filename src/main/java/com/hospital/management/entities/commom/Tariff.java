package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name= "tariff")
public class Tariff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "tariff_id")
    private Integer tariffId;

    @Column(name= "tariff_code")
    private String tariffCode;

    @Column(name= "tariff_name")
    private String tariffName;

    @Column(name= "tariff_desc")
    private String tariffDescription;

    @Column(name= "status")
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
