package com.hospital.management.service;

import com.hospital.management.entities.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    List<Country> getAllCountryNames();

    Country findCountryById(Long countryId);

    Country saveCountry(Country country);

    Country updateCountry(Country country, Long countryId);

    String deleteCountryById(Long countryId);
}
