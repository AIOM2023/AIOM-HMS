package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "common_reason_for_discount")
public class ReasonForDiscount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "reason_for_discount_id")
    private Integer reasonForDiscountId;

    @Column(name= "reason_for_discount_code")
    private String reasonForDiscountCode;

    @Column(name= "reason_for_discount_value")
    private  String reasonForDiscountValue;

    @Column(name= "reason_for_discount_description")
    private String reasonForDiscountDescription;

}
