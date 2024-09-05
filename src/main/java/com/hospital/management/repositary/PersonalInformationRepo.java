package com.hospital.management.repositary;

import com.hospital.management.entities.commom.PersonalInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalInformationRepo extends JpaRepository<PersonalInformation, Integer > {
}
