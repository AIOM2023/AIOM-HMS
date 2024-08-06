package com.hospital.management.repositary;

import com.hospital.management.entities.commom.RegistrationFees;
import com.hospital.management.entities.commom.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RegistrationFeesRepo extends JpaRepository<RegistrationFees, Integer > {

    @Query(value = "SELECT * FROM reg_fees t WHERE t.status = 0", nativeQuery = true)
    List<RegistrationFees> findAllRegistrationFees();


    Optional<RegistrationFees> findByRegFeesIdAndStatus(Integer regFeesId, Integer status);

    @Query(value = "UPDATE reg_fees SET status = 1 WHERE reg_fees_id = :regFeesId", nativeQuery = true)
    @Modifying
    void deleteRegFeeById(@Param("regFeesId") Integer regFeesId);
}
