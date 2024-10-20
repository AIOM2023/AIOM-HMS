package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "company_info")
public class CompanyInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "company_id")
    private Long companyId;

    @Column(name= "company_name")
    private String companyName;

    @Column(name= "company_address")
    private String companyAddress;

    @Column(name= "company_contact_no")
    private String companyContactNo;

    @Column(name= "tin")
    private String tinNo;

    @Lob
    @Column(name= "logo",columnDefinition = "LONGBLOB")
    private byte[] logo;


}
