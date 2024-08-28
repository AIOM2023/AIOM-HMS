package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name= "insurance_comp")
public class InsuranceComp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "in_com_id")
    private Long insComId;

    @Column(name= "comp_id")
    private String companyId;

    @Column(name= "ins_org")
    private Integer insOrg;

    @Column(name= "company_name")
    private String companyName;

    @Column(name= "company_address")
    private String companyAddress;

    @Column(name= "phone_no")
    private String phoneNo;

    @Column(name= "fax_no")
    private String faxNo;

    @Column(name= "gst")
    private String gst;

    @Column(name= "email_address")
    private String emailAddress;

    @Column(name= "contact_person")
    private String contactPerson;

    @Column(name= "contact_no_person")
    private String contactNoPerson;

    @Column(name= "contact_email")
    private String contactEmail;

    @Column(name= "notes")
    private String notes;

    @Column(name= "contract_date")
    private OffsetDateTime contractDate;

    @Column(name= "valid_from")
    private OffsetDateTime validFrom;

    @Column(name= "valid_to")
    private OffsetDateTime validTo;

    @Column(name= "ipp_type")
    private String ippType;

    @Column(name= "ipp_disc")
    private String ippDisc;

    @Column(name= "ipb_type")
    private String ipbType;

    @Column(name= "ipb_disc")
    private String ipbDisc;

    @Column(name= "created_date")
    private OffsetDateTime createdDate;

    @Column(name= "created_by")
    private String createdBy;

    @Column(name= "modified_date")
    private OffsetDateTime modifiedDate;

    @Column(name= "modified_by")
    private String modifiedBy;

    @Column(name= "del_ins")
    private Integer delIns;

    @Column(name= "status")
    private Integer status;

}
