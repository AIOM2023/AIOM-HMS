package com.hospital.management.service;

import com.hospital.management.entities.Country;
import com.hospital.management.entities.commom.NurseStation;

import java.util.List;

public interface NurseStationService {

    List<NurseStation> getAllNurseStation();

    NurseStation findNurseStationId(Integer nurseStationId);

    NurseStation saveNurseStation(NurseStation nurseStation);

    NurseStation updatenurseStation(NurseStation nurseStation, Integer nurseStationId);

    String deleteNurseStationById(Integer nurseStationId);
}
