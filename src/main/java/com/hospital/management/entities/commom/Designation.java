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
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "designation")
public class Designation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "designation_id")
    private Long designationId;

    @Column(name ="designation_code")
    private String designationCode;

    @Column(name="designation")
    private String designation;

    @Column(name="description")
    private String description;

    @Column(name="created_date")
    @CreationTimestamp
    private OffsetDateTime createdDate;

    @Column(name="created_by")
    @Builder.Default
    private String  createdBy="System";

    @Column(name="modified_date")
    @UpdateTimestamp
    private OffsetDateTime modifiedDate;

    @Column(name="modified_by")
    private String  modifiedBy;

    @Column(name= "status")
    @Builder.Default
    private Integer status = 0;

}