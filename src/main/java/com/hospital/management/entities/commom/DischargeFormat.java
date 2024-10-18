package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "dischrg_fmt")
public class DischargeFormat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "disc_fmt_id")
    private Long discFmtId;

    @Column(name= "dept_id")
    private Integer deptId;

    @Column(name="dis_fmt_code")
    private String disfmtCode;

    @Column(name="dis_fmt_name")
    private String disFmtName;

    @Column(name="dis_fmt_descr")
    private String disFmtDescr;

    @Column(name="created_date")
    @CreationTimestamp
    private OffsetDateTime createdDate;

    @Column(name="created_by")
    @Builder.Default
    private String  createdBy = "System";

    @Column(name="modified_date")
    private OffsetDateTime modifiedDate;

    @Column(name="modified_by")
    @UpdateTimestamp
    private String  modifiedBy;

   @Column(name= "status")
   @Builder.Default
    private Integer status = 0;
}
