package com.hospital.management.repositary;

import com.hospital.management.entities.commom.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TariffRepo extends JpaRepository<Tariff, Integer> {
    @Query(value = "SELECT * FROM tariff t WHERE t.del_tariff = 0", nativeQuery = true)
    List<Tariff> findAllTariffs();

    @Query(value = "UPDATE tariff SET del_tariff = 1 WHERE tariff_id = :tariffId", nativeQuery = true)
    @Modifying
    void deleteTariffById(@Param("tariffId") Integer tariffId);

}
