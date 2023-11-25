package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "common_medicine_type")
public class MedicineType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "medicine_type_id")
    private Integer medicineTypeId;

    @Column(name= "medicine_type_code")
    private String medicineTypeCode;

    @Column(name= "medicine_type_value")
    private String medicineTypeValue;

    @Column(name= "medicine_type_description")
    private String medicineDescription;
}
