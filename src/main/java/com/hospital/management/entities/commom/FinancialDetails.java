package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name= "financial_details")
public class FinancialDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "financial_id")
    private Long financialId;

    @Column(name= "tax_id_no")
    private String taxIdNo;

    @Column(name="epf_no")
    private String epfNo;

    @Column(name="payment_mode")
    private String paymentMode;

    @Column(name= "ledger_no")
    private String ledgerNo;

    @Column(name="account_no")
    private String account_no;

    @Column(name="bank_name")
    private String bankName;

    @Column(name="bank_code")
    private String bankCode;

    @Column(name="bank_address")
    private String bankAddress;

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

}
