package com.hospital.management.repositary;

import com.hospital.management.entities.State;
import com.hospital.management.entities.commom.SystemParameters;
import com.hospital.management.entities.response.StateNameId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StateRepo extends JpaRepository<State, Long> {
    @Query(value = "SELECT s FROM State s WHERE (s.stateCode like CONCAT('%', ?1, '%') OR s.stateName LIKE CONCAT('%', ?1, '%'))  AND s.status = 0 ORDER BY ?#{#pageable}")
    Page<State> findAllStates(String search, Pageable pageable);

    Optional<State> findByStateIdAndStatus(Long stateId, Integer status);

    @Query(value = "UPDATE master_state SET status = 1 WHERE STATE_ID = :stateId", nativeQuery = true)
    @Modifying
    void deleteStateById(@Param("stateId") Long stateId);
    State findByStateName(String stateName);
    @Query(value = "SELECT max(STATE_ID) FROM master_state", nativeQuery = true)
    Long getMaxId();

    @Query(value = "SELECT STATE_NAME,STATE_ID FROM master_state WHERE COUNTRY_ID = :countryId", nativeQuery = true)
    List<StateNameId> findAllStateNamesAndStateId(Long countryId);
}
