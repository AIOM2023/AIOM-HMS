package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.Branches;
import com.hospital.management.entities.response.BranchesSearchResult;
import com.hospital.management.exceptions.HmsBusinessException;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.payload.ErrorResponse;
import com.hospital.management.repositary.BranchesRepo;
import com.hospital.management.service.BranchesService;
import com.hospital.management.utils.HmsCommonUtil;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BranchesServiceImpl implements BranchesService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BranchesServiceImpl.class);
    private static final String REFERRAL_NOT_FOUND = "Branches not found with the given Id: %s";
    @Autowired
    BranchesRepo branchesRepo;

    @Override
    public Branches saveBranches(Branches branches) {
        LOGGER.info("Creating a new Branches");

        Long maxId = branchesRepo.getMaxId();
        branches.setBranchCode("BR-"+(maxId == null ? 1 : maxId+1));
        branches.setCreatedBy("System");
        branches.setCreatedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        branches.setStatus(0);
        return branchesRepo.save(branches);
    }
    @Override
    public Branches updateBranche(Branches branches, Long branchId) {
        LOGGER.info("Updating an existing branch");
        if(!isBranchExist(branchId)) {
            LOGGER.error("update() - branch not found with the given Id: {} ", branchId);
            throw new ResourceNotFoundException(String.format(REFERRAL_NOT_FOUND, branchId));
        }
        branches.setModifiedBy("System");
        branches.setModifiedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        return branchesRepo.save(branches);
    }

    @Override
    public BranchesSearchResult branchesList(String search, int pageNo, int pageSize, String sortBy, String sortOrder) {
        LOGGER.info("find all Branches List");
        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Branches> pages = branchesRepo.findAllBranches(search, pageable);

        return mapToBranchesSearchResult(pageNo, pageSize, pages.getContent());

    }
    private BranchesSearchResult mapToBranchesSearchResult(int pageNo, int pageSize, List<Branches> branches) {
        BranchesSearchResult branchesSearchResult = new BranchesSearchResult();
        Long totalPages = (long) (branches.size() % pageSize == 0 ? branches.size() / pageSize : branches.size() / pageSize+1);
        branchesSearchResult.setMetaData(HmsCommonUtil.getMetaData((long) branches.size(), totalPages, pageNo, pageSize));
        branchesSearchResult.setData(branches);

        return branchesSearchResult;
    }


    @Override
    public Branches findBranchesById(Long branchId) {
        LOGGER.info("Fetching branches by id");
        Optional<Branches> branches = branchesRepo.findBybranchIdAndStatus(branchId, 0);
        return branches.orElseThrow(() ->
                new ResourceNotFoundException(String.format(REFERRAL_NOT_FOUND, branchId)));
    }
    @Transactional
    @Override
    public String deleteBranchesById(Long branchId) {
        if(!isBranchExist(branchId)) {
            LOGGER.error("isBranchExist() - Branches not found with the given Id: {} ", branchId);
            throw new ResourceNotFoundException(String.format(REFERRAL_NOT_FOUND, branchId));
        }
        try{
            branchesRepo.deleteBranchById(branchId);
        } catch (Exception ex){
            LOGGER.error("isBranchExist() - Unable to delete Branches with the given Id: {} ", branchId);
            ErrorResponse errorResponse = new ErrorResponse("500", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new HmsBusinessException(String.format("Unable to delete Branches with the given Id: %s", branchId), errorResponse);
        }

        return "Branches deleted successfully!";
    }

    private boolean isBranchExist(Long branchId){
        return branchesRepo.findBybranchIdAndStatus(branchId, 0).isPresent();
    }


}
