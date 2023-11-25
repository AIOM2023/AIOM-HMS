package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "common_blood_group")
public class BloodGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "blood_group_id")
    private Integer bloodGroupId;

    @Column(name= "blood_group_code")
    private String bloodGroupCode;

    @Column(name= "blood_group_value")
    private String bloodGroupName;

    @Column(name= "blood_group_description")
    private String bloodGroupDescription;
}
