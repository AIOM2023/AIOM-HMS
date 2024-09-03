package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name= "reg_fees")
public class RegistrationFees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "reg_fees_id")
    private Long regFeesId;

    @Column(name= "reg_fee_code")
    private String regFeeCode;

    @Column(name= "reg_fees_name")
    private String regFeesName;

    @Column(name= "reg_fees")
    private float regFees;

    @Column(name= "reg_valid")
    private OffsetDateTime regValid;

    @Column(name= "reg_valid_to")
    private OffsetDateTime regValidTo;

    @Column(name= "reg_fees_desc")
    private String regFeesDesc;

    @Column(name= "created_date")
    private OffsetDateTime createdDate;

    @Column(name= "created_by")
    private String createdBy;

  //  @Column(name= "created_id")
   // private String createdId;

    @Column(name= "modified_date")
    private OffsetDateTime modifiedDate;

    @Column(name= "modified_by")
    private String modifiedBy;

  //  @Column(name= "modified_id")
    //private String modifiedId;

   // @Column(name= "del_reg_fee")
    //private String delRegFee;

    @Column(name= "status")
    private Integer status;

}
