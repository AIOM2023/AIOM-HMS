package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name= "room_group")
public class RoomGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "room_group_id")
    private Long roomGroupId;

    @Column(name= "room_group_code")
    private String roomGroupCode;

    @Column(name= "room_group_name")
    private String roomGroupName;

    @Column(name= "description")
    private String description;

    @Column(name= "status")
    private Integer status;

    @Column(name="created_date")
    private OffsetDateTime createdDate;

    @Column(name="created_by")
    private String  createdBy;

    @Column(name="modified_date")
    private OffsetDateTime modifiedDate;

    @Column(name="modified_by")
    private String  modifiedBy;
}
