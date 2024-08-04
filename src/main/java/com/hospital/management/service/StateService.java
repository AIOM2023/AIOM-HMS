package com.hospital.management.service;

import com.hospital.management.entities.Country;
import com.hospital.management.entities.State;

import java.util.List;

public interface StateService {

    List<State> getAllStates();

    State findStateById(Long stateId);

    State saveState(State state);

    State updateState(State state, Long stateId);

    String deleteStateById(Long stateId);

}
