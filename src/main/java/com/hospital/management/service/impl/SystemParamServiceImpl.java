package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.SystemParameters;
import com.hospital.management.entities.search.SystemParametersSearchList;
import com.hospital.management.exceptions.DuplicateEntryException;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.repositary.SystemParamsRepo;
import com.hospital.management.service.SystemParametersService;
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
import java.util.Optional;


@Service
public class SystemParamServiceImpl implements SystemParametersService {

    private static final Logger log = LoggerFactory.getLogger(SystemParamServiceImpl.class);

    @Autowired
    SystemParamsRepo systemParamsRepo;

    @Override
    public SystemParameters save(SystemParameters systemParameters) {
        SystemParameters systemParameterExisting = systemParamsRepo.findByCommonValue(systemParameters.getCommonValue());
        if (systemParameterExisting != null) {
            throw new DuplicateEntryException("A system parameter with the name '" + systemParameterExisting.getCommonValue() + "' already exists.");
        }
        return systemParamsRepo.save(systemParameters);
    }

    @Override
    public SystemParameters update(SystemParameters systemParameters) {
        SystemParameters systemParameterExisting = systemParamsRepo.findByCommonValue(systemParameters.getCommonValue());
        if (systemParameterExisting != null) {
            throw new DuplicateEntryException("A system parameter with the name '" + systemParameterExisting.getCommonValue() + "' already exists.");
        }
        return systemParamsRepo.save(systemParameters);
    }

    @Override
    public SystemParameters delete(Long paramId) {
        SystemParameters systemParameters=systemParamsRepo.findById(paramId)
                .orElseThrow(() -> new RuntimeException("Id Not Found"));
        systemParameters.setStatus(1);
        systemParamsRepo.save(systemParameters);
        return systemParameters;
    }

    @Override
    public SystemParametersSearchList getSystemParametersList(String search, int pageNo, int pageSize, String sortBy, String sortOrder) {
        log.info("Fetching all System Parameters");
        int actualPage = pageNo - 1; // Pages in Spring Data start from 0
        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
        Pageable pageable = PageRequest.of(actualPage, pageSize,sort);
        Page<SystemParameters> systemParametersListPages= systemParamsRepo.findAllSystemParams(search,pageable);

        return mapToSysParamsSearchResult(pageNo,pageSize,systemParametersListPages);
    }

    @Override
    public List<SystemParameters> getSystemParametersListByMainId(Long paramsMainId) {
        Optional<List<SystemParameters>> systemParametersbyMainId = systemParamsRepo.findAllByParamsMainId(paramsMainId);
        return  systemParametersbyMainId.orElseThrow(() ->
                new ResourceNotFoundException(String.format("sysParamsMainId not found with the given Id: %s", paramsMainId)));
    }

    @Override
    public List<SystemParameters> getSystemParametersListById(Long paramId) {
        Optional<List<SystemParameters>> systemParametersByMainId = systemParamsRepo.findAllByParamId(paramId);
        return  systemParametersByMainId.orElseThrow(() ->
                new ResourceNotFoundException(String.format("sysParamsMainId not found with the given Ids: %s", paramId)));
    }

    @Override
    public List<SystemParameters> getSystemParametersWithIds(List<Long> paramIds){
        Optional<List<SystemParameters>> systemParametersByIds = systemParamsRepo.findAllByParamsIds(paramIds);
        return systemParametersByIds.orElseThrow(()->
                new ResourceNotFoundException("sysParamsMainId not found with the given Ids:"));
    }



    private SystemParametersSearchList mapToSysParamsSearchResult(int pageNo, int pageSize, Page<SystemParameters> pages) {
        SystemParametersSearchList systemParametersSearchList = new SystemParametersSearchList();
        systemParametersSearchList.setMetaData(HmsCommonUtil.getMetaData((long) pages.getTotalElements(), (long) pages.getTotalPages(), pageNo, pageSize));
        systemParametersSearchList.setData(pages.getContent());
        return systemParametersSearchList;
    }
}
