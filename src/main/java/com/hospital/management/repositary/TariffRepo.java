package com.hospital.management.repositary;

import com.hospital.management.entities.commom.Tariff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TariffRepo extends JpaRepository<Tariff, Long> {
    @Query(value = "SELECT * FROM tariff WHERE tariff_code like %?1% OR tariff_name like %?1% " +
            "AND status = 0 ORDER BY ?#{#pageable}",
            nativeQuery = true)
    Page<Tariff> findAllTariffs(String search, Pageable pageable);


    Optional<Tariff> findByTariffIdAndStatus(Long tariffId, Integer status);

    @Query(value = "UPDATE tariff SET status = 1 WHERE tariff_id = :tariffId", nativeQuery = true)
    @Modifying
    void deleteTariffById(@Param("tariffId") Long tariffId);

    @Query(value = "SELECT max(tariff_id) FROM tariff", nativeQuery = true)
    Long getMaxId();

    List<Tariff> findByStatus(Integer status);

    List<Tariff> findByTariffIdIn(List<Long> ids);

}
