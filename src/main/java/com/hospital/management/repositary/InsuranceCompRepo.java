package com.hospital.management.repositary;

import com.hospital.management.entities.commom.InsuranceComp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InsuranceCompRepo extends JpaRepository<InsuranceComp, Integer > {

    @Query(value = "SELECT * FROM insurance_comp r WHERE r.status = 0", nativeQuery = true)
    List<InsuranceComp> findAllInsuranceComp();


    Optional<InsuranceComp> findByInsuranceCompIdAndStatus(Integer insComId, Integer status);

    @Query(value = "UPDATE insurance_comp SET status = 1 WHERE in_com_id = :insComId", nativeQuery = true)
    @Modifying
    void deleteInsuranceCompIdById(@Param("insComId") Integer insComId);

    @Query(value = "SELECT max(in_com_id) FROM insurance_comp", nativeQuery = true)
    Long getMaxId();
}