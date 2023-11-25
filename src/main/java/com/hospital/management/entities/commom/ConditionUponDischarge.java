package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "common_condition_upon_discharge")
public class ConditionUponDischarge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "condition_upon_discharge_id")
    private Integer conditionUponDischargeId;

    @Column(name= "condition_upon_discharge_code")
    private String conditionUponDischargeCode;

    @Column(name= "condition_upon_discharge_value")
    private String conditionUponDischargeValue;

    @Column(name= "condition_upon_discharge_description")
    private  String conditionUponDischargeDescription;

}
