package com.hospital.management.service;

import com.hospital.management.entities.City;
import com.hospital.management.entities.response.CityNameId;
import com.hospital.management.entities.response.CitySearchResult;

import java.util.List;

public interface CityService {

    CitySearchResult getAllCities(String search, int pageNo, int pageSize, String sortBy, String sortOrder);

    City findCityById(Long cityId);

    City saveCity(City city);

    City updateCity(City city, Long cityId);

    String deleteCityById(Long cityId);


    List<CityNameId> getAllCityNames(Long districtId);
}