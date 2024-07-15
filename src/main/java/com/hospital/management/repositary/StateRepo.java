package com.hospital.management.repositary;

import com.hospital.management.entities.Country;
import com.hospital.management.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StateRepo extends JpaRepository<State, Integer> {
    @Query(value = "SELECT * FROM master_state c WHERE c.status = 0", nativeQuery = true)
    List<State> findAllStates();

    Optional<State> findByStateIdAndStatus(Integer stateId, Integer status);

    @Query(value = "UPDATE master_state SET status = 1 WHERE STATE_ID = :stateId", nativeQuery = true)
    @Modifying
    void deleteStateById(@Param("stateId") Integer stateId);
}
