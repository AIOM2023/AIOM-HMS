package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "system_parameters_main")
public class SystemParametersMain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "params_main_id")
    private Integer paramsMainId;

    @Column(name= "param_name",unique = true)
    private String paramName;


}
