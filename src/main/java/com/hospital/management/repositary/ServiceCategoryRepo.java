package com.hospital.management.repositary;

import com.hospital.management.entities.commom.ServiceCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceCategoryRepo extends JpaRepository<ServiceCategory,Integer> {
}
