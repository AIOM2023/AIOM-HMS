package com.hospital.management.service;

import com.hospital.management.entities.District;

import java.util.List;

public interface DistrictService {
    List<District> getAllDistricts();

    District findDistrictById(Long districtId);

    District saveDistrict(District district);

    District updateDistrict(District district, Long districtId);

    String deleteDistrictById(Long districtId);

    List<String> getAllDistrictNames(String stateName);

}
