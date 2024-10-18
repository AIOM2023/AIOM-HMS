package com.hospital.management.service;

import com.hospital.management.entities.District;
import com.hospital.management.entities.response.DistrictNameId;
import com.hospital.management.entities.response.DistrictSearchResult;

import java.util.List;

public interface DistrictService {
    DistrictSearchResult getAllDistricts(String search, int pageNo, int pageSize, String sortBy, String sortOrder);

    List<District> findDistrictById(Long districtId);

    District saveDistrict(District district);

    District updateDistrict(District district, Long districtId);

    String deleteDistrictById(Long districtId);

    List<District> getAllDistrictNames(List<Long> stateId);

    List<District> districtListAll();

}
