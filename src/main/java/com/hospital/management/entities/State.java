package com.hospital.management.entities;

import com.hospital.management.entities.commom.Department;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "master_state")
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "STATE_ID")
    private  Long stateId;

    @Column(name="STATE_CODE")
    private  String stateCode;

    @Column(name="STATE_NAME")
    private  String stateName;

  /*  @Column(name="COUNTRY_NAME")
    private String countryName;*/

    private Integer status;

    @Column(name="created_by")
    private String createdBy;

    @Column(name="created_date")
    private OffsetDateTime createdDate;

    @Column(name="modified_by")
    private String  modifiedBy;

    @Column(name="modified_date")
    private OffsetDateTime modifiedDate;

    @ManyToOne
    @JoinColumn(name = "COUNTRY_ID")
    private Country country;

}
