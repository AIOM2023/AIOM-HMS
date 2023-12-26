package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name= "corp_quick_entry")
public class CorpQuickEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "corp_quick_id")
    private Integer corpQuickId;

    @Column(name= "tariff_cd")
    private String tariffCd;

    @Column(name= "tariff_name")
    private String tariffName;

    @Column(name= "srv_grp_cd")
    private String srvGrpCd;

    @Column(name= "srv_grp_name")
    private String srvGrpName;

    @Column(name= "service")
    private String service;

    @Column(name= "ser_name")
    private String serName;

    @Column(name= "charge")
    private Integer charge;

    @Column(name= "app_for")
    private String appFor;

    @Column(name= "corp_service")
    private String corpService;

    @Column(name= "corp_ser_name")
    private String corpSerName;

    @Column(name= "corp_charge")
    private Integer corpCharge;

    @Column(name= "corp_appfor")
    private String corpAppFor;

    @Column(name= "created_date")
    private OffsetDateTime createdDate;

    @Column(name= "created_by")
    private String createdBy;

    @Column(name= "modified_date")
    private OffsetDateTime modifiedDate;

    @Column(name= "modified_by")
    private String modifiedBy;

    @Column(name= "created_id")
    private String createdId;

    @Column(name= "modified_id")
    private String modifiedId;

    @Column(name= "corp_quick_entry_status")
    private Integer corpQuickEntryStatus;

    @Column(name= "in_active")
    private Integer inActive;

    @Column(name= "general_ward")
    private String generalWard;

    @Column(name= "non_ac_rooms")
    private String nonAcRooms;

    @Column(name= "iccu")
    private String iccu;

}
