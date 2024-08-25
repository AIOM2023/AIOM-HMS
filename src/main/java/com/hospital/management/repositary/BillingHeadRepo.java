package com.hospital.management.repositary;

import com.hospital.management.entities.commom.BillingHead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BillingHeadRepo extends JpaRepository<BillingHead,Long> {
    @Query(value = "SELECT * FROM billing_head WHERE status = 0", nativeQuery = true)
    List<BillingHead> findAllBillingHeads();

    Optional<BillingHead> findByBillingHeadIdAndStatus(Long billingHeadId, Integer status);

    @Query(value = "UPDATE billing_head SET status = 1 WHERE billing_head_id = :billingHeadId", nativeQuery = true)
    @Modifying
    void deleteBillingHeadById(@Param("billingHeadId") Long billingHeadId);

    @Query(value = "SELECT max(billing_head_id) FROM billing_head", nativeQuery = true)
    Long getMaxId();
}
