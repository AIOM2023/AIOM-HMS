package com.hospital.management.service;

import com.hospital.management.entities.State;
import com.hospital.management.entities.response.StateSearchResult;

public interface StateService {

    StateSearchResult getAllStates(String search, int pageNo, int pageSize, String sortBy, String sortOrder);

    State findStateById(Long stateId);

    State saveState(State state);

    State updateState(State state, Long stateId);

    String deleteStateById(Long stateId);

    List<String> getAllStateNames(String countryName);

}
