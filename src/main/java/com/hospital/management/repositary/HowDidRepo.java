package com.hospital.management.repositary;

import com.hospital.management.entities.commom.HowDid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface HowDidRepo extends JpaRepository<HowDid, Integer > {


    @Query(value = "SELECT * FROM how_did WHERE how_did_desc like %?1% OR how_did_name like %?1% " +
            "AND status = 0 ORDER BY ?#{#pageable}",
            nativeQuery = true)
    Page<HowDid> findAllHowDidList(String search, Pageable pageable);

    Optional<HowDid> findByHowDidIdAndStatus(Long howDidId, Integer status);

    @Query(value = "UPDATE how_did SET status = 1 WHERE how_did_id = :howDidId", nativeQuery = true)
    @Modifying
    void deleteHowDidById(@Param("howDidId") Long howDidId);

    @Query(value = "SELECT max(how_did_id) FROM how_did", nativeQuery = true)
    Long getMaxId();
}
