package com.hospital.management.service;

import com.hospital.management.entities.City;
import com.hospital.management.entities.District;

import java.util.List;

public interface DistrictService {
    List<District> getAllDistricts();

    District findDistrictById(Integer districtId);

    District saveDistrict(District district);

    District updateDistrict(District district, Integer districtId);

    String deleteDistrictById(Integer districtId);
}
