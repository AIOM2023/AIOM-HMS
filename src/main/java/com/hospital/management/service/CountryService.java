package com.hospital.management.service;

import com.hospital.management.entities.Country;

import java.util.List;

public interface CountryService {

    List<Country> getAllCountryNames();

    Country saveCountry(Country country);

    Country updateCountry(Country country, Integer countryId);

    String deleteCountryById(Integer countryId);
}
