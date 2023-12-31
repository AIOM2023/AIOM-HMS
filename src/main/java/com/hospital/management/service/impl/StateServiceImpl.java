package com.hospital.management.service.impl;

import com.hospital.management.entities.State;
import com.hospital.management.repositary.StateRepo;
import com.hospital.management.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateServiceImpl implements StateService {

    @Autowired
    private StateRepo stateRepo;

    @Override
    public List<State> getAllStateNames() {
        return stateRepo.findAll();
    }
}
