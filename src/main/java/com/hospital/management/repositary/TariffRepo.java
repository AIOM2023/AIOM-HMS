package com.hospital.management.repositary;

import com.hospital.management.entities.commom.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TariffRepo extends JpaRepository<Tariff, Integer> {
    @Query(value = "SELECT * FROM tariff t WHERE t.status = 0", nativeQuery = true)
    List<Tariff> findAllTariffs();


    Optional<Tariff> findByTariffIdAndStatus(Integer tariffId, Integer status);

    @Query(value = "UPDATE tariff SET status = 1 WHERE tariff_id = :tariffId", nativeQuery = true)
    @Modifying
    void deleteTariffById(@Param("tariffId") Integer tariffId);

}
