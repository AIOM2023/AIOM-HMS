package com.hospital.management.service.impl;

import com.hospital.management.entities.City;
import com.hospital.management.entities.Country;
import com.hospital.management.exceptions.HmsBusinessException;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.payload.ErrorResponse;
import com.hospital.management.repositary.CityRepo;
import com.hospital.management.service.CityService;
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
public class CityServiceImpl implements CityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

    @Autowired
    private CityRepo cityRepo;


    @Override
    public List<City> getAllCities() {
        LOGGER.info("Fetching all cities");
        return cityRepo.findAllCities();
    }

    @Override
    public City findCityById(Long cityId) {
        LOGGER.info("Fetching city by id");
        Optional<City> city = cityRepo.findByCityIdAndStatus(cityId, 0);
        return city.orElseThrow(() ->
                new ResourceNotFoundException(String.format("City not found with the given Id: %s", cityId)));
    }

    @Override
    public City saveCity(City city) {
        LOGGER.info("Creating a new city");

        Long maxId = cityRepo.getMaxId();
        city.setCityCode("CT-"+(maxId == null ? 1 : maxId+1));

        city.setCreatedBy("System");
        city.setCreatedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        city.setStatus(0);
        return cityRepo.save(city);
    }

    @Override
    public City updateCity(City city, Long cityId) {
        if(!isCityExist(cityId)) {
            LOGGER.error("updateCity() - Given cityId is not exist");
            throw new ResourceNotFoundException(String.format("City not found with the given Id: %s", cityId));
        }
        city.setModifiedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        city.setModifiedBy("System");

        return cityRepo.save(city);
    }


    @Transactional
    @Override
    public String deleteCityById(Long cityId) {
        if(!isCityExist(cityId)) {
            LOGGER.error("deleteCityById() - Given cityId is not exist");
            throw new ResourceNotFoundException(String.format("City not found with the given Id: %s", cityId));
        }
        try{
            cityRepo.deleteCityById(cityId);
        } catch (Exception ex){
            LOGGER.error("deleteCityById() - Unable to delete City with the given Id: {}", cityId);
            ErrorResponse errorResponse = new ErrorResponse("500", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new HmsBusinessException(String.format("Unable to delete City with the given Id: %s", cityId), errorResponse);
        }

        return "City deleted successfully!";
    }


    private boolean isCityExist(Long cityId){
        return cityRepo.findByCityIdAndStatus(cityId, 0).isPresent();
    }
}
