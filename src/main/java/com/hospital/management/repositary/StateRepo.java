package com.hospital.management.repositary;

import com.hospital.management.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StateRepo extends JpaRepository<State, Long> {
    @Query(value = "SELECT * FROM master_state c WHERE c.status = 0", nativeQuery = true)
    List<State> findAllStates();

    Optional<State> findByStateIdAndStatus(Long stateId, Integer status);

    @Query(value = "UPDATE master_state SET status = 1 WHERE STATE_ID = :stateId", nativeQuery = true)
    @Modifying
    void deleteStateById(@Param("stateId") Long stateId);

    @Query(value = "SELECT max(STATE_ID) FROM master_state", nativeQuery = true)
    Long getMaxId();

    @Query(value = "SELECT STATE_NAME FROM master_state WHERE COUNTRY_NAME = :countryName", nativeQuery = true)
    List<String> findAllStateNames(String countryName);
}
