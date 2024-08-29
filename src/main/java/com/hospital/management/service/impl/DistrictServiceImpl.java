package com.hospital.management.service.impl;

import com.hospital.management.entities.District;
import com.hospital.management.exceptions.HmsBusinessException;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.payload.ErrorResponse;
import com.hospital.management.repositary.DistrictRepo;
import com.hospital.management.service.DistrictService;
import com.hospital.management.utils.HmsCommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DistrictServiceImpl implements DistrictService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DistrictServiceImpl.class);

    @Autowired
    private DistrictRepo districtRepo;

    @Override
    public List<District> getAllDistricts() {
        LOGGER.info("Fetching all Districts");
        return districtRepo.findAllDistricts();
    }

    @Override
    public District findDistrictById(Long districtId) {
        LOGGER.info("Fetching District by id");
        Optional<District> district = districtRepo.findByDistrictIdAndStatus(districtId, 0);
        return district.orElseThrow(() ->
                new ResourceNotFoundException(String.format("District not found with the given Id: %s", districtId)));
    }

    @Override
    public District saveDistrict(District district) {
        LOGGER.info("Creating a new district");

        Long maxId = districtRepo.getMaxId();
        district.setDistrictCode("DT-"+(maxId == null ? 1 : maxId+1));

        district.setCreatedBy("System");
        district.setCreatedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        district.setStatus(0);
        return districtRepo.save(district);
    }

    @Override
    public District updateDistrict(District district, Long districtId) {
        if(!isDistrictExist(districtId)) {
            LOGGER.error("updateDistrict() - Given districtId is not exist");
            throw new ResourceNotFoundException(String.format("District not found with the given Id: %s", districtId));
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
    public List<String> getAllDistrictNames(String stateName) {
        LOGGER.info("Fetching all district names");
        return districtRepo.findAllDistrictNames(stateName);
    }

    private boolean isDistrictExist(Long districtId){
        return districtRepo.findByDistrictIdAndStatus(districtId, 0).isPresent();
    }
}
