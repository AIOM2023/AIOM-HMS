package com.hospital.management.service;

import com.hospital.management.entities.Country;
import com.hospital.management.entities.response.CountrySearchResult;

import java.util.List;

public interface CountryService {

    CountrySearchResult getAllCountries(String search, int pageNo, int pageSize, String sortBy, String sortOrder);

    List<Country> findCountryById(Long countryId);

    Country saveCountry(Country country);

    Country updateCountry(Country country, Long countryId);

    String deleteCountryById(Long countryId);

    List<Country> countryListAll();
}
