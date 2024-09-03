package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

@Data
@Entity
@Table(name= "system_parameters_main")
public class SystemParametersMain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "params_main_id")
    private Long paramsMainId;

    @Column(name= "param_name",unique = true)
    @NotNull(message = "paramName is mandatory")
    @NotBlank(message = "paramNa is mandatory and cannot be blank")
    private String paramName;


}
