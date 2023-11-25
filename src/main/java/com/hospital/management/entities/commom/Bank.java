package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "common_bank")
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "bank_id")
    private Integer bankId;

    @Column(name= "bank_code")
    private String bankCode;

    @Column(name= "bank_value")
    private String bankValue;

    @Column(name= "bank_description")
    private String bankDescription;
}
