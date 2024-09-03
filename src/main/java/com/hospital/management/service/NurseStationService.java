package com.hospital.management.service;

import com.hospital.management.entities.commom.NurseStation;
import com.hospital.management.entities.response.NurseStationSearchResult;

public interface NurseStationService {

    NurseStationSearchResult getAllNurseStation(String search, int pageNo, int pageSize, String sortBy, String sortOrder);

    NurseStation findNurseStationId(Long nurseStationId);

    NurseStation saveNurseStation(NurseStation nurseStation);

    NurseStation updatenurseStation(NurseStation nurseStation, Long nurseStationId);

    String deleteNurseStationById(Long nurseStationId);
}
