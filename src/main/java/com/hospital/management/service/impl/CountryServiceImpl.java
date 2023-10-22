package com.hospital.management.service.impl;

import com.hospital.management.entities.CountryModel;
import com.hospital.management.repositary.CountryRepo;
import com.hospital.management.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepo countryRepo;

    @Override
    public List<CountryModel> getAllCountryNames() {
        return countryRepo.findAll();
    }
}
