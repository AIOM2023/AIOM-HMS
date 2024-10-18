package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@Entity
@Table(name= "consult_charge")
public class ConsultCharge implements Serializable {
    @Serial
    private static final long serialVersionUID = 3399899935845660779L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "consult_id")
    private Long consultId;

    @Column(name= "consult_code")
    private String consultCode;

    @Column(name= "tariff_id")
    private Long tariffId;

    @Transient
    private String tariffName;

    @Column(name= "consultant_id")
    private Long consultantID;

    @Transient
    private String consultantName;

    @Column(name= "billing_head_id")
    private Long billingHeadId;

    @Transient
    private String billingHeadName;
    
    @Column(name= "registration_fee")
    private Integer registrationFee;

    @Column(name= "no_of_days")
    private Integer noOfDays;

    @Column(name= "no_of_visits")
    private Integer noOfVisits;

    @Column(name= "discount_op")
    private Integer discountOp;

    @Column(name= "discount_ip")
    private Integer discountIp;

    @Column(name="service_tax")
    private Double serviceTax;

    private Integer status;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="con_fk_id")
    private List<WardCharge> wardCharges;
    
    @Column(name= "created_date")
    private OffsetDateTime createdDate;

    @Column(name= "created_by")
    private String createdBy;

    @Column(name= "modified_date")
    private OffsetDateTime modifiedDate;

    @Column(name= "modified_by")
    private String modifiedBy;

}
