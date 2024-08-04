package com.hospital.management.service;

import com.hospital.management.entities.City;
import com.hospital.management.entities.Country;

import java.util.List;

public interface CityService {

    List<City> getAllCities();

    City findCityById(Long cityId);

    City saveCity(City city);

    City updateCity(City city, Long cityId);

    String deleteCityById(Long cityId);


}