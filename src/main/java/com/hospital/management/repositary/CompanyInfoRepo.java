package com.hospital.management.repositary;


import com.hospital.management.entities.commom.CompanyInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyInfoRepo extends JpaRepository<CompanyInfo, Integer> {
}
