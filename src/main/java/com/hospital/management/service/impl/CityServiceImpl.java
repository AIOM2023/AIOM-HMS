package com.hospital.management.service.impl;

import com.hospital.management.entities.City;
import com.hospital.management.entities.response.CitySearchResult;
import com.hospital.management.exceptions.HmsBusinessException;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.payload.ErrorResponse;
import com.hospital.management.repositary.CityRepo;
import com.hospital.management.service.CityService;
import com.hospital.management.utils.HmsCommonUtil;
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
public class CityServiceImpl implements CityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

    @Autowired
    private CityRepo cityRepo;


    @Override
    public CitySearchResult getAllCities(String search, int pageNo, int pageSize, String sortBy, String sortOrder) {
        LOGGER.info("Fetching all cities");
        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<City> pages = cityRepo.findAllCities(search, pageable);
         return mapToCitySearchResult(pageNo, pageSize, pages.getContent());
    }
    private CitySearchResult mapToCitySearchResult(int pageNo, int pageSize, List<City> citys) {
        CitySearchResult citySearchResult = new CitySearchResult();
        Long totalPages = (long) (citys.size() % pageSize == 0 ? citys.size() / pageSize : citys.size() / pageSize+1);
        citySearchResult.setMetaData(HmsCommonUtil.getMetaData((long) citys.size(), totalPages, pageNo, pageSize));
        citySearchResult.setData(citys);

        return citySearchResult;
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
