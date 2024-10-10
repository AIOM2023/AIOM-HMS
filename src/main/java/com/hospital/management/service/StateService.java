package com.hospital.management.service;

import com.hospital.management.entities.State;
import com.hospital.management.entities.response.StateNameId;
import com.hospital.management.entities.response.StateSearchResult;

import java.util.List;

public interface StateService {

    StateSearchResult getAllStates(String search, int pageNo, int pageSize, String sortBy, String sortOrder);

    List<State> findStateById(Long stateId);

    List<State> stateListAll();

    State saveState(State state);

    State updateState(State state, Long stateId);

    String deleteStateById(Long stateId);

    List<StateNameId> getAllStateNames(Long countryId);

}
