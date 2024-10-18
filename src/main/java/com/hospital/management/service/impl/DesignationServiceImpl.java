package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.Designation;
import com.hospital.management.entities.search.DesignationSearchResult;
import com.hospital.management.exceptions.DuplicateEntryException;
import com.hospital.management.repositary.DesignationRepo;
import com.hospital.management.service.DesignationService;
import com.hospital.management.utils.HmsCommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DesignationServiceImpl implements DesignationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DesignationServiceImpl.class);

    @Autowired
    DesignationRepo designationRepo;

    @Override
    public DesignationSearchResult designationList(String search, int pageNo, int pageSize, String sortBy, String sortOrder) {
        LOGGER.info("Fetching all DesignationList");
        int actualPage = pageNo - 1; // Pages in Spring Data start from 0
        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
        Pageable pageable = PageRequest.of(actualPage, pageSize,sort);
        Page<Designation>  designationPages = designationRepo.findAllDesignations(search, pageable);
        return mapToDesignationSearchResult(pageNo, pageSize, designationPages);
    }

    @Override
    public List<Designation> findDesignationById(Long designationId) {
        LOGGER.info("Fetching Department by id");
        return designationRepo.findByDesignationId(designationId);
    }

    @Override
    public List<Designation> designationListAll() {
        return designationRepo.findAllDesifnationList();
    }


    @Override
    public Designation saveDesignation(Designation designation) {
        LOGGER.info("Creating a new Designation");
        Designation designationNameExisting = designationRepo.findByDesignation(designation.getDesignation());
        if (designationNameExisting != null) {
            throw new DuplicateEntryException("A system parameter with the name '" + designationNameExisting.getDesignation() + "' already exists.");
        }
        Long maxId = designationRepo.getMaxId();
        designation.setDesignationCode("DES" +(maxId == null ? 1 : maxId+1));
        return designationRepo.save(designation);
    }

    @Override
    public Designation updateDesignation(Designation designation) {
        LOGGER.info("Updating an existing Designation");
        Designation designationNameExisting = designationRepo.findByDesignation(designation.getDesignation());
        if(designationNameExisting != null && !(designationNameExisting.getDesignationId().equals(designation.getDesignationId()))){
            if (designationNameExisting.getDesignation().equals(designation.getDesignation())){
                throw new DuplicateEntryException("Department with the name '" + designationNameExisting.getDesignation() + "' already exists.");
            }
        }
        return designationRepo.save(designation);
    }
    @Override
    public Designation deleteDesignationById(Long designationId) {
        Designation designation=designationRepo.findById(designationId)
                .orElseThrow(() -> new RuntimeException("Id Not Found"));
        designation.setStatus(1);
        designationRepo.save(designation);
        return designation;
    }

    private DesignationSearchResult mapToDesignationSearchResult(int pageNo, int pageSize, Page<Designation> designationPages) {
        DesignationSearchResult designationSearchResult = new DesignationSearchResult();
        designationSearchResult.setMetaData(HmsCommonUtil.getMetaData(designationPages.getTotalElements(), (long) designationPages.getTotalPages(), pageNo, pageSize));
        designationSearchResult.setData(designationPages.getContent());

        return designationSearchResult;
    }

}
