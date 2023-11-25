package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "common_payment_type")
public class paymentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "payment_type_id")
    private Integer paymentTypeId;

    @Column(name= "payment_type_code")
    private String paymentCode;

    @Column(name= "payment_type_value")
    private String paymentValue;

    @Column(name= "payment_type_description")
    private String paymentDescription;

}
