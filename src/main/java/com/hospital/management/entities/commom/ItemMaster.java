package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "common_item_master")
public class ItemMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "item_master_id")
    private Integer itemMasterId;

    @Column(name= "item_master_code")
    private  String itemMasterCode;

    @Column(name= "item_master_value")
    private  String itemMasterValue;

    @Column(name= "item_master_description")
    private String itemMasterDescription;

}
