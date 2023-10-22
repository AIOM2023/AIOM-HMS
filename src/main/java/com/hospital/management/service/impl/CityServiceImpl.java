package com.hospital.management.service.impl;

import com.hospital.management.entities.CityModel;
import com.hospital.management.repositary.CityRepo;
import com.hospital.management.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepo cityRepo;

    @Override
    public List<CityModel> getAllCityNames() {
        return cityRepo.findAll();
    }


}
