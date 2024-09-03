package com.hospital.management.service;

import com.hospital.management.entities.commom.Specialization;
import com.hospital.management.entities.response.SpecializationSearchResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SpecializationService {
    SpecializationSearchResult getAllSpecializations(String search, int pageNo, int pageSize, String sortBy, String sortOrder);

    Specialization findSpecializationById(Long specializationId);

    Specialization saveSpecialization(Specialization specialization);

    Specialization updateSpecialization(Specialization specialization, Long specializationId);

    @Transactional
    String deleteSpecializationById(Long specializationId);
}
