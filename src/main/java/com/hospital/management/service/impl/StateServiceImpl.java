package com.hospital.management.service.impl;

import com.hospital.management.entities.Country;
import com.hospital.management.entities.State;
import com.hospital.management.exceptions.HmsBusinessException;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.payload.ErrorResponse;
import com.hospital.management.repositary.StateRepo;
import com.hospital.management.service.StateService;
import com.hospital.management.utils.HmsCommonUtil;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StateServiceImpl implements StateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StateServiceImpl.class);

    @Autowired
    private StateRepo stateRepo;

    @Override
    public List<State> getAllStates() {
        LOGGER.info("Fetching all countries");
        return stateRepo.findAllStates();
    }

    @Override
    public State findStateById(Long stateId) {
        LOGGER.info("Fetching state by id");
        Optional<State> state = stateRepo.findByStateIdAndStatus(stateId, 0);
        return state.orElseThrow(() ->
                new ResourceNotFoundException(String.format("Satate not found with the given Id: %s", stateId)));
    }

    @Override
    public State saveState(State state) {
        LOGGER.info("Creating a new state");

        Long maxId = stateRepo.getMaxId();
        state.setStateCode("ST-"+(maxId == null ? 1 : maxId+1));

        state.setCreatedBy("System");
        state.setCreatedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        state.setStatus(0);
        return stateRepo.save(state);
    }

    @Override
    public State updateState(State state, Long stateId) {
        if(!isSateExist(stateId)) {
            LOGGER.error("updateState() - Given stateId is not exist");
            throw new ResourceNotFoundException(String.format("State not found with the given Id: %s", stateId));
        }
        state.setModifiedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        state.setModifiedBy("System");
        return stateRepo.save(state);
    }

    @Transactional
    @Override
    public String deleteStateById(Long stateId) {
        if(!isSateExist(stateId)) {
            LOGGER.error("deleteStateById() - Given stateId is not exist");
            throw new ResourceNotFoundException(String.format("State not found with the given Id: %s", stateId));
        }
        try{
            stateRepo.deleteStateById(stateId);
        } catch (Exception ex){
            LOGGER.error("deleteStateById() - Unable to delete State with the given Id: {}", stateId);
            ErrorResponse errorResponse = new ErrorResponse("500", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new HmsBusinessException(String.format("Unable to delete State with the given Id: %s", stateId), errorResponse);
        }

        return "State deleted successfully!";
    }

    private boolean isSateExist(Long stateId){
        return stateRepo.findByStateIdAndStatus(stateId, 0).isPresent();
    }
}
