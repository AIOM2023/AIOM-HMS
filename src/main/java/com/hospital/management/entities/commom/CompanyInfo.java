package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Blob;

@Data
@Entity
@Table(name= "company_info")
public class CompanyInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "cmp_id")
    private Integer companyId;

    @Column(name= "company_name")
    private String companyName;

    @Column(name= "company_address")
    private String companyAddress;

    @Column(name= "company_contact_no")
    private String companyContactNo;

    @Column(name= "tin")
    private String tinNo;

    @Lob
    @Column(name= "logo")
    private byte[] logo;


}
