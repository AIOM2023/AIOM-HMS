package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.Tariff;
import com.hospital.management.exceptions.HmsBusinessException;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.payload.ErrorResponse;
import com.hospital.management.repositary.TariffRepo;
import com.hospital.management.service.TariffService;
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
public class TariffServiceImpl implements TariffService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CountryServiceImpl.class);

    @Autowired
    TariffRepo tariffRepo;

    @Override
    public Tariff save(Tariff tariff) {
        LOGGER.info("Creating a new Tariff");
        tariff.setCreatedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        tariff.setCreatedBy("System");
        tariff.setStatus(0);
        return tariffRepo.save(tariff);
    }

    @Override
    public Tariff update(Tariff tariff, Integer tariffId) {
        LOGGER.info("Updating an existing Tariff");
        if(!isTariffExist(tariffId)) {
            LOGGER.error("update() - Tariff not found with the given Id: {} ", tariffId);
            throw new ResourceNotFoundException(String.format("Tariff not found with the given Id: %s", tariffId));
        }
        tariff.setModifiedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        tariff.setModifiedBy("System");
        return tariffRepo.save(tariff);
    }

    @Override
    public List<Tariff> tariffList() {
        LOGGER.info("Fetching all Tariffs");
        return tariffRepo.findAllTariffs();
    }

    @Override
    public Tariff findTariffById(Integer tariffId) {
        LOGGER.info("Fetching tariff by id");
        Optional<Tariff> tariff = tariffRepo.findByTariffIdAndStatus(tariffId, 0);
        return tariff.orElseThrow(() ->
                new ResourceNotFoundException(String.format("Tariff not found with the given Id: %s", tariffId)));
    }

    @Transactional
    @Override
    public String deleteTariffById(Integer tariffId) {
        if(!isTariffExist(tariffId)) {
            LOGGER.error("deleteTariffById() - Tariff not found with the given Id: {} ", tariffId);
            throw new ResourceNotFoundException(String.format("Tariff not found with the given Id: %s", tariffId));
        }
        try{
            tariffRepo.deleteTariffById(tariffId);
        } catch (Exception ex){
            LOGGER.error("deleteTariffById() - Unable to delete Tariff with the given Id: {} ", tariffId);
            ErrorResponse errorResponse = new ErrorResponse("500", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new HmsBusinessException(String.format("Unable to delete Tariff with the given Id: %s", tariffId), errorResponse);
        }

        return "Tariff deleted successfully!";
    }

    private boolean isTariffExist(Integer tariffId){
        return tariffRepo.findByTariffIdAndStatus(tariffId, 0).isPresent();
    }
}
