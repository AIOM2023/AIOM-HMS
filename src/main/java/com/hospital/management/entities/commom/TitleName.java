package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "common_title_name")
public class TitleName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "title_name_id")
    private Integer titleId;

    @Column(name= "title_name_code")
    private String titleCode;

    @Column(name= "title_name_value")
    private String titleValue;

    @Column(name= "title_name_description")
    private String titleDescription;
}
