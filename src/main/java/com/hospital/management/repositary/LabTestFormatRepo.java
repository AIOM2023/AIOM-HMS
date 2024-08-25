package com.hospital.management.repositary;

import com.hospital.management.entities.commom.LabTestFormat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LabTestFormatRepo extends JpaRepository<LabTestFormat, Long> {
    @Query(value = "SELECT * FROM lab_test_format WHERE status = 0", nativeQuery = true)
    List<LabTestFormat> findAllTestFormats();

    Optional<LabTestFormat> findByTestFormatIdAndStatus(Long testFormatId, Integer status);

    @Query(value = "UPDATE lab_test_format SET status = 1 WHERE test_format_id = :testFormatId", nativeQuery = true)
    @Modifying
    void deleteTestFormatById(@Param("testFormatId") Long testFormatId);

    @Query(value = "SELECT max(test_format_id) FROM lab_test_format", nativeQuery = true)
    Long getMaxId();
}
