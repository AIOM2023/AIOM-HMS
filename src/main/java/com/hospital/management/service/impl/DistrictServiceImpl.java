package com.hospital.management.service.impl;

import com.hospital.management.entities.District;
import com.hospital.management.entities.State;
import com.hospital.management.entities.response.DistrictNameId;
import com.hospital.management.entities.response.DistrictSearchResult;
import com.hospital.management.exceptions.DuplicateEntryException;
import com.hospital.management.exceptions.HmsBusinessException;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.payload.ErrorResponse;
import com.hospital.management.repositary.DistrictRepo;
import com.hospital.management.service.DistrictService;
import com.hospital.management.utils.HmsCommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DistrictServiceImpl implements DistrictService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DistrictServiceImpl.class);

    @Autowired
    private DistrictRepo districtRepo;

    @Override
    public DistrictSearchResult getAllDistricts(String search, int pageNo, int pageSize, String sortBy, String sortOrder) {
        int actualPage = pageNo - 1; // Pages in Spring Data start from 0
        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
        Pageable pageable = PageRequest.of(actualPage, pageSize,sort);
       // Page<Country> pages = countryRepo.findAllCountries(search, pageable);
        Page<District> pages = districtRepo.findAllDistricts(search, pageable);
        return mapToDistrictSearchResult(pageNo, pageSize,pages);
    }
    private DistrictSearchResult mapToDistrictSearchResult(int pageNo, int pageSize, Page<District> pages) {
        DistrictSearchResult districtSearchResult = new DistrictSearchResult();
        districtSearchResult.setMetaData(HmsCommonUtil.getMetaData((long) pages.getTotalElements(), (long) pages.getTotalPages(), pageNo, pageSize));
        districtSearchResult.setData(pages.getContent());
        return districtSearchResult;
    }
    @Override
    public List<District> findDistrictById(Long districtId) {
        LOGGER.info("Fetching District by id");
        return districtRepo.findByDistrictId(districtId);
    }


    @Override
    public List<District> districtListAll() {
        return districtRepo.findAllDistrictList();
    }

    @Override
    public District saveDistrict(District district) {
        LOGGER.info("Creating a new district");
        District districtExisting = districtRepo.findByDistrictName(district.getDistrictName());
        if (districtExisting != null) {
            throw new DuplicateEntryException("A City with the name '" + districtExisting.getDistrictName() + "' already exists.");
        }
        Long maxId = districtRepo.getMaxId();
        district.setDistrictCode("DT-"+(maxId == null ? 1 : maxId+1));

        district.setCreatedBy("System");
        district.setCreatedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        district.setStatus(0);
        return districtRepo.save(district);
    }

    @Override
    public District updateDistrict(District district, Long districtId) {
        District districtExisting = districtRepo.findByDistrictName(district.getDistrictName());
        if(districtExisting != null && !(districtExisting.getDistrictId().equals(district.getDistrictId()))){
            if (districtExisting.getDistrictName().equals(district.getDistrictName())){
                throw new DuplicateEntryException("Department with the name '" + districtExisting.getDistrictName() + "' already exists.");
            }
        }
        district.setModifiedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        district.setModifiedBy("System");

        return districtRepo.save(district);
    }

    @Transactional
    @Override
    public String deleteDistrictById(Long districtId) {
        if(!isDistrictExist(districtId)) {
            LOGGER.error("deleteDistrictById() - Given districtId is not exist");
            throw new ResourceNotFoundException(String.format("District not found with the given Id: %s", districtId));
        }
        try{
            districtRepo.deleteDistrictById(districtId);
        } catch (Exception ex){
            LOGGER.error("deleteDistrictById() - Unable to delete District with the given Id: {}", districtId);
            ErrorResponse errorResponse = new ErrorResponse("500", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new HmsBusinessException(String.format("Unable to delete District with the given Id: %s", districtId), errorResponse);
        }

        return "District deleted successfully!";
    }

    @Override
    public List<District> getAllDistrictNames(List<Long> stateId) {
        LOGGER.info("Fetching all district names");
        Optional<List<District>> districtByIds  = districtRepo.findAllDistrictNamesAndDistrictId(stateId);
        return districtByIds.orElseThrow(()->
                new ResourceNotFoundException("districtByIds not found with the given Ids:"));


    }

    private boolean isDistrictExist(Long districtId){
        return districtRepo.findByDistrictIdAndStatus(districtId, 0).isPresent();
    }
}
