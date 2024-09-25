package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name= "branches")
public class Branches {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "branche_id")
    private Long branchId;

    @Column(name="branche_code")
    private String branchCode;

    @Column(name="branche_name")
    private String branchName;

    @Column(name="created_date")
    private OffsetDateTime createdDate;

    @Column(name="created_by")
    private String  createdBy;

    @Column(name="modified_date")
    private OffsetDateTime modifiedDate;

    @Column(name="modified_by")
    private String  modifiedBy;

    /*@Column(name= "del_branch")
    private Integer delBranch;*/

    @Column(name= "status")
    private Integer status;

    @Column(name= "country")
    private String country;

    @Column(name= "state")
    private String state;

    @Column(name= "city")
    private String city;

    @Column(name= "pin_code")
    private String pinCode;

    @Column(name= "district")
    private String district;

    @Column(name= "street")
    private String street;

}
