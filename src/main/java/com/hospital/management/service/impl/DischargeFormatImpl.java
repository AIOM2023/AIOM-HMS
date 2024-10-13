package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.DischargeFormat;
import com.hospital.management.entities.search.DischargeFormatSearchResult;
import com.hospital.management.exceptions.DuplicateEntryException;
import com.hospital.management.repositary.DischargeFormatRepo;
import com.hospital.management.utils.HmsCommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DischargeFormatImpl implements DischargeFormatService {

    @Autowired
    DischargeFormatRepo dischargeFormatRepo;

    @Override
    public DischargeFormatSearchResult dischargeFormatList(String search, int pageNo, int pageSize, String sortBy, String sortOrder) {
        log.info("find all dischargeFormat List");
        int actualPage = pageNo - 1; // Pages in Spring Data start from 0
        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
        Pageable pageable = PageRequest.of(actualPage, pageSize,sort);
        Page<DischargeFormat> dischargeFormatPages = dischargeFormatRepo.findAllDischargeFormat(search, pageable);
        return mapToDischargeFormatSearchResult(pageNo, pageSize, dischargeFormatPages);
    }

    @Override
    public List<DischargeFormat> findDischargeFormatById(Long discFmtId) {
        log.info("Fetching DischargeFormat by id");
        return dischargeFormatRepo.findByDiscFmtId(discFmtId);
    }

    @Override
    public List<DischargeFormat> dischargeFormatListAll() {
        return dischargeFormatRepo.findAllDischargeFormatList();
    }

    @Override
    public DischargeFormat saveDischargeFormat(DischargeFormat dischargeFormat) {
        log.info("Creating a new DischargeFormat");
        DischargeFormat dischargeFormatExisting = dischargeFormatRepo.findByDisFmtName(dischargeFormat.getDisFmtName());
        if (dischargeFormatExisting != null) {
            throw new DuplicateEntryException("A system parameter with the name '" + dischargeFormatExisting.getDisFmtName() + "' already exists.");
        }
        Long maxId = dischargeFormatRepo.getMaxId();
        dischargeFormat.setDisfmtCode("DF-"+(maxId == null ? 1 : maxId+1));
        return dischargeFormatRepo.save(dischargeFormat);
    }

    @Override
    public DischargeFormat updateDischargeFormat(DischargeFormat dischargeFormat) {
        log.info("Updating an existing DischargeFormat");
        DischargeFormat dischargeFormatExisting = dischargeFormatRepo.findByDisFmtName(dischargeFormat.getDisFmtName());
        if(dischargeFormatExisting != null && !(dischargeFormatExisting.getDiscFmtId().equals(dischargeFormat.getDiscFmtId()))){
            if (dischargeFormatExisting.getDisFmtName().equals(dischargeFormat.getDisFmtName())){
                throw new DuplicateEntryException("Department with the name '" + dischargeFormatExisting.getDisFmtName() + "' already exists.");
            }
        }
        return dischargeFormatRepo.save(dischargeFormat);
    }

    @Override
    public DischargeFormat deleteDischargeFormatById(Long discFmtId) {
        DischargeFormat dischargeFormat=dischargeFormatRepo.findById(discFmtId)
                .orElseThrow(() -> new RuntimeException("Id Not Found"));
        dischargeFormat.setStatus(1);
        dischargeFormatRepo.save(dischargeFormat);
        return dischargeFormat;
    }

    private DischargeFormatSearchResult mapToDischargeFormatSearchResult(int pageNo, int pageSize, Page<DischargeFormat> dischargeFormatPages) {
        DischargeFormatSearchResult dischargeFormatSearchResult = new DischargeFormatSearchResult();
        dischargeFormatSearchResult.setMetaData(HmsCommonUtil.getMetaData(dischargeFormatPages.getTotalElements(),(long) dischargeFormatPages.getTotalPages(), pageNo, pageSize));
        dischargeFormatSearchResult.setData(dischargeFormatPages.getContent());
        return dischargeFormatSearchResult;
    }

}
