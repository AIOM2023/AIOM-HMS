package com.hospital.management.repositary;

import com.hospital.management.entities.Country;
import com.hospital.management.entities.commom.RegistrationFees;
import com.hospital.management.entities.commom.Tariff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RegistrationFeesRepo extends JpaRepository<RegistrationFees, Integer > {

   /* @Query(value = "SELECT * FROM reg_fees t WHERE t.status = 0", nativeQuery = true)
    List<RegistrationFees> findAllRegistrationFees();*/

    @Query(value = "SELECT * FROM reg_fees WHERE reg_fee_code like %?1% OR reg_fees_name like %?1% " +
            "AND status = 0 ORDER BY ?#{#pageable}",
            nativeQuery = true)
    Page<RegistrationFees> findAllRegistrationFees(String search, Pageable pageable);


    Optional<RegistrationFees> findByRegFeesIdAndStatus(Long regFeesId, Integer status);

    @Query(value = "UPDATE reg_fees SET status = 1 WHERE reg_fees_id = :regFeesId", nativeQuery = true)
    @Modifying
    void deleteRegFeeById(@Param("regFeesId") Long regFeesId);

    @Query(value = "SELECT max(reg_fees_id) FROM reg_fees", nativeQuery = true)
    Long getMaxId();
}
