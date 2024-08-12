package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "system_pages")
public class SystemPages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "system_pages_id")
    private Integer systemPagesId;

    @Column(name="page_module")
    private String pageModule;

    @Column(name="page_name")
    private String pageName;

    @Column(name="page_link")
    private String pageLink;

    @Column(name="status")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "params_main_id")
    private SystemParametersMain systemParametersMain;


}
