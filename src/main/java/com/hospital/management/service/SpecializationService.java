package com.hospital.management.service;

import com.hospital.management.entities.commom.Specialization;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SpecializationService {
    List<Specialization> getAllSpecializations();

    Specialization findSpecializationById(Long specializationId);

    Specialization saveSpecialization(Specialization specialization);

    Specialization updateSpecialization(Specialization specialization, Long specializationId);

    @Transactional
    String deleteSpecializationById(Long specializationId);
}
