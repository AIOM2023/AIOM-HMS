package com.hospital.management.repositary;

import com.hospital.management.entities.commom.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpecializationRepo extends JpaRepository<Specialization, Long> {

    @Query(value = "SELECT * FROM specialization_master sp WHERE sp.status = 0", nativeQuery = true)
    List<Specialization> findAllSpecializations();

    Optional<Specialization> findBySpecializationIdAndStatus(Long specializationId, Integer status);

    @Query(value = "UPDATE specialization_master SET status = 1 WHERE specialization_id = :specializationId", nativeQuery = true)
    @Modifying
    void deleteSpecializationById(@Param("specializationId") Long specializationId);

    @Query(value = "SELECT max(specialization_id) FROM specialization_master", nativeQuery = true)
    Long getMaxId();
}
