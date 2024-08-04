package com.hospital.management.repositary;

import com.hospital.management.entities.admin.Referral;
import com.hospital.management.entities.commom.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReferralRepo extends JpaRepository<Referral, Long> {

    @Query(value = "SELECT * FROM master_referral r WHERE r.status = 0", nativeQuery = true)
    List<Referral> findAllReferrals();


    Optional<Referral> findByReferralIdAndStatus(Long referralId, Integer status);

    @Query(value = "UPDATE master_referral SET status = 1 WHERE referral_id = :referralId", nativeQuery = true)
    @Modifying
    void deleteReferralById(@Param("referralId") Long referralId);

    @Query(value = "SELECT max(referral_id) FROM master_referral", nativeQuery = true)
    Long getMaxId();
}
