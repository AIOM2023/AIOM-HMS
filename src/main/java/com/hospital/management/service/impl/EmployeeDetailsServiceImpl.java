package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.EmployeeDetails;
import com.hospital.management.entities.response.EmployeeDetailsSearchResult;
import com.hospital.management.exceptions.HmsBusinessException;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.payload.ErrorResponse;
import com.hospital.management.repositary.EmployeeDetailsRepo;
import com.hospital.management.service.EmployeeDetailsService;
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
public class EmployeeDetailsServiceImpl implements EmployeeDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDetailsServiceImpl.class);

    @Autowired
    private EmployeeDetailsRepo employeeDetailsRepo;

    @Override
    public EmployeeDetailsSearchResult getAllEmployeeDetails(String search, int pageNo, int pageSize, String sortBy, String sortOrder) {
        LOGGER.info("Fetching all countries");
        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<EmployeeDetails> pages = employeeDetailsRepo.findAllEmployeeDetails(search, pageable);

        return mapToEmployeeDetailsSearchResult(pageNo, pageSize, pages.getContent());
    }

    @Override
    public EmployeeDetails findEmployeeDetailsById(Long employeeId) {
        LOGGER.info("Fetching EmployeeDetails by id");
        Optional<EmployeeDetails> employeeDetails = employeeDetailsRepo.findByEmployeeIdAndStatus(employeeId, 0);
        return employeeDetails.orElseThrow(() ->
                new ResourceNotFoundException(String.format("EmployeeDetails not found with the given Id: %s", employeeId)));
    }

    @Override
    public EmployeeDetails saveEmployeeDetails(EmployeeDetails employeeDetails) {
        LOGGER.info("Creating a new country");

        Long maxId = employeeDetailsRepo.getMaxId();
        employeeDetails.setEmployeeCode("ED" +(maxId == null ? 1 : maxId+1));

        employeeDetails.setCreatedBy("System");
        employeeDetails.setCreatedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        employeeDetails.setStatus(0);
        return employeeDetailsRepo.save(employeeDetails);
    }


    @Override
    public EmployeeDetails updateEmployeeDetails(EmployeeDetails employeeDetails, Long employeeId) {
        if(!isEmployeeExist(employeeId)) {
            LOGGER.error("updateCountry() - Given employeeDetails id is not exist");
            throw new ResourceNotFoundException(String.format("employeeDetails not found with the given Id: %s", employeeId));
        }
        employeeDetails.setModifiedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        employeeDetails.setModifiedBy("System");
        return employeeDetailsRepo.save(employeeDetails);
    }

    @Transactional
    @Override
    public String deleteEmployeeDetailsById(Long employeeId) {
        if(!isEmployeeExist(employeeId)) {
            LOGGER.error("deleteEmployeeDetailsById() - Given employeeId is not exist");
            throw new ResourceNotFoundException(String.format("Country not found with the given Id: %s", employeeId));
        }
        try{
            employeeDetailsRepo.deleteEmployeeDetailsById(employeeId);
        } catch (Exception ex){
            LOGGER.error("deleteEmployeeDetailsById() - Unable to delete employee with the given Id: {}", employeeId);
            ErrorResponse errorResponse = new ErrorResponse("500", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new HmsBusinessException(String.format("Unable to delete employee with the given Id: %s", employeeId), errorResponse);
        }

        return "employee deleted successfully!";
    }

    private boolean isEmployeeExist(Long employeeId){
        return employeeDetailsRepo.findByEmployeeIdAndStatus(employeeId, 0).isPresent();
    }

    private EmployeeDetailsSearchResult mapToEmployeeDetailsSearchResult (int pageNo, int pageSize, List<EmployeeDetails> employeeDetails) {
        EmployeeDetailsSearchResult employeeDetailsSearchResult = new EmployeeDetailsSearchResult();
        Long totalPages = (long) (employeeDetails.size() % pageSize == 0 ? employeeDetails.size() / pageSize : employeeDetails.size() / pageSize+1);
        employeeDetailsSearchResult.setMetaData(HmsCommonUtil.getMetaData((long) employeeDetails.size(), totalPages, pageNo, pageSize));
        employeeDetailsSearchResult.setData(employeeDetails);

        return employeeDetailsSearchResult;
    }
}
