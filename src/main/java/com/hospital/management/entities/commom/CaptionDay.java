package com.hospital.management.entities.commom;


import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name= "caption_day")
public class CaptionDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "caption_id")
    private Integer captionId;

    @Column(name= "caption_code")
    private String captionCode;

    @Column(name= "caption_name")
    private String captionName;

    @Column(name= "from_dt")
    private OffsetDateTime fromDt;

    @Column(name= "to_date")
    private OffsetDateTime toDate;

    @Column(name= "in_active")
    private Integer inActive;

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

    @Column(name= "caption_status")
    private Integer captionStatus;

    @Column(name= "appr_caption")
    private Integer apprCaption;

    @Column(name= "del_caption")
    private Integer delCaption;

    @Column(name= "caption_for_occation")
    private String captionForOccation;
}
