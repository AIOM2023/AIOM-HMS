package com.hospital.management.service.impl;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.hospital.management.entities.Country;
import com.hospital.management.entities.response.CountrySearchResult;
import com.hospital.management.exceptions.DuplicateEntryException;
import com.hospital.management.exceptions.HmsBusinessException;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.payload.ErrorResponse;
import com.hospital.management.repositary.CountryRepo;
import com.hospital.management.service.CountryService;
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

import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryServiceImpl.class);

    @Autowired
    private CountryRepo countryRepo;

    @Override
    public CountrySearchResult getAllCountries(String search, int pageNo, int pageSize, String sortBy, String sortOrder) {
        LOGGER.info("Fetching all countries");
        int actualPage = pageNo - 1; // Pages in Spring Data start from 0
        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
        Pageable pageable = PageRequest.of(actualPage, pageSize,sort);
        Page<Country> pages = countryRepo.findAllCountries(search, pageable);

        return mapToCountrySearchResult(pageNo, pageSize,pages);
    }

    @Override
    public Country findCountryById(Long countryId) {
        LOGGER.info("Fetching country by id");
        Optional<Country> country = countryRepo.findByCountryIdAndStatus(countryId, 0);
        return country.orElseThrow(() ->
                new ResourceNotFoundException(String.format("Country not found with the given Id: %s", countryId)));
    }

    @Override
    public Country saveCountry(Country country) {
        LOGGER.info("Creating a new country");

        countryRepo.findByCountryNameAndStatus(country.getCountryName(), 0)
                .ifPresent(cn -> {
            throw new DuplicateEntryException("A County with the name '" + cn.getCountryName() + "' already exists.");
        });

        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        country.setCountryCode(HmsCommonUtil.getCountryCodeFromName(country.getCountryName()));
        country.setCountryPhoneCode("+"+ phoneNumberUtil.getCountryCodeForRegion(country.getCountryCode()));
        country.setCreatedBy("System");
        country.setCreatedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        country.setStatus(0);
        return countryRepo.save(country);
    }


    @Override
    public Country updateCountry(Country country, Long countryId) {
        if(!isCountryExist(countryId)) {
            LOGGER.error("updateCountry() - Given countryId is not exist");
            throw new ResourceNotFoundException(String.format("Country not found with the given Id: %s", countryId));
        }
        country.setModifiedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        country.setModifiedBy("System");
        return countryRepo.save(country);
    }

    @Transactional
    @Override
    public String deleteCountryById(Long countryId) {
        if(!isCountryExist(countryId)) {
            LOGGER.error("deleteCountryById() - Given countryId is not exist");
            throw new ResourceNotFoundException(String.format("Country not found with the given Id: %s", countryId));
        }
        try{
            countryRepo.deleteCountryById(countryId);
        } catch (Exception ex){
            LOGGER.error("deleteCountryById() - Unable to delete Country with the given Id: {}", countryId);
            ErrorResponse errorResponse = new ErrorResponse("500", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new HmsBusinessException(String.format("Unable to delete Country with the given Id: %s", countryId), errorResponse);
        }

        return "Country deleted successfully!";
    }

    private boolean isCountryExist(Long countryId){
        return countryRepo.findByCountryIdAndStatus(countryId, 0).isPresent();
    }

    private CountrySearchResult mapToCountrySearchResult(int pageNo, int pageSize,Page<Country> pages) {
        CountrySearchResult countrySearchResult = new CountrySearchResult();
        countrySearchResult.setMetaData(HmsCommonUtil.getMetaData((long) pages.getTotalElements(), (long) pages.getTotalPages(), pageNo, pageSize));
        countrySearchResult.setData(pages.getContent());
        return countrySearchResult;
    }
}
