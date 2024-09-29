package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name="created_date")
    @CreationTimestamp
    private OffsetDateTime createdDate;

    @Column(name="created_by")
    @Builder.Default
    private String  createdBy = "System";

    @Column(name="modified_date")
    @UpdateTimestamp
    private OffsetDateTime modifiedDate;

    @Column(name="modified_by")
    private String  modifiedBy;
}
