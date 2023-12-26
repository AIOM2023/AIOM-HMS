package com.hospital.management.repositary;


import com.hospital.management.entities.commom.ConsultCharge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultChargeRepo extends JpaRepository<ConsultCharge,Integer> {
}
