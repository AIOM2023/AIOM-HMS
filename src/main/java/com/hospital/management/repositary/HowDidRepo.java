package com.hospital.management.repositary;

import com.hospital.management.entities.commom.HowDid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HowDidRepo extends JpaRepository<HowDid, Integer > {

    @Query(value = "SELECT * FROM how_did t WHERE t.status = 0", nativeQuery = true)
    List<HowDid> findAllHowDidList();


    Optional<HowDid> findByHowDidIdAndStatus(Integer howDidId, Integer status);

    @Query(value = "UPDATE how_did SET status = 1 WHERE how_did_id = :howDidId", nativeQuery = true)
    @Modifying
    void deleteHowDidById(@Param("howDidId") Integer howDidId);
}
