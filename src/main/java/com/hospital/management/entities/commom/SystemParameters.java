package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "common_system_parameters")
public class SystemParameters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "param_id")
    private Long paramId;

    @Column(name= "comm_code")
    private String commonCode;

    @Column(name= "comm_value")
    private String commonValue;

    @Column(name= "comm_desc")
    private String commonDesc;

    @Column(name= "status")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "params_main_id")
    private SystemParametersMain systemParametersMain;

}
