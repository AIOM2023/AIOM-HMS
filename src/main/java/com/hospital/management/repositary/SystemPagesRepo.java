package com.hospital.management.repositary;

import com.hospital.management.entities.commom.SystemPages;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SystemPagesRepo extends JpaRepository<SystemPages, Integer> {

    @Query("SELECT a FROM SystemPages a WHERE a.status = 0")
    List<SystemPages> getSystemPagesList();

}
