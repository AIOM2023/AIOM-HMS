package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.Tariff;
import com.hospital.management.exceptions.HmsBusinessException;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.payload.ErrorResponse;
import com.hospital.management.repositary.TariffRepo;
import com.hospital.management.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TariffServiceImpl implements TariffService {

    @Autowired
    TariffRepo tariffRepo;

    @Override
    public Tariff save(Tariff tariff) {
        tariff.setCreatedDate(OffsetDateTime.now());
        tariff.setCreatedBy("System");
        return tariffRepo.save(tariff);
    }

    @Override
    public Tariff update(Tariff tariff, Integer tariffId) {
        if(!isTariffExist(tariffId)) {
            throw new ResourceNotFoundException(String.format("Tariff not found with the given Id: %s", tariffId));
        }
        tariff.setModifiedDate(OffsetDateTime.now());
        tariff.setModifiedBy("System");
        return tariffRepo.save(tariff);
    }

    @Override
    public List<Tariff> tariffList() {
        return tariffRepo.findAllTariffs();
    }

    @Override
    public String deleteTariffById(Integer tariffId) {
        if(!isTariffExist(tariffId)) {
            throw new ResourceNotFoundException(String.format("Tariff not found with the given Id: %s", tariffId));
        }
        try{
            tariffRepo.deleteTariffById(tariffId);
        } catch (Exception ex){
            ErrorResponse errorResponse = new ErrorResponse("500", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new HmsBusinessException(String.format("Unable to delete Tariff with the given Id: %s", tariffId), errorResponse);
        }

        return "Tariff deleted successfully!";
    }

    private boolean isTariffExist(Integer tariffId){
        Optional<Tariff> tariff = tariffRepo.findById(tariffId);
        return tariff.isPresent();
    }
}
