package com.hospital.management.service;

import com.hospital.management.entities.Country;
import com.hospital.management.entities.response.CountrySearchResult;

public interface CountryService {

    CountrySearchResult getAllCountries(String search, int pageNo, int pageSize, String sortBy, String sortOrder);

    Country findCountryById(Long countryId);

    Country saveCountry(Country country);

    Country updateCountry(Country country, Long countryId);

    String deleteCountryById(Long countryId);
}
