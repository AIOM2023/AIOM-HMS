package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.Tariff;
import com.hospital.management.entities.response.TariffSearchResult;
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
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames={"tariff"})
public class TariffServiceImpl implements TariffService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CountryServiceImpl.class);

    @Autowired
    TariffRepo tariffRepo;

    @Autowired
    private CacheManager cacheManager;

    @Override
    public Tariff save(Tariff tariff) {
        LOGGER.info("Creating a new Tariff");

        Long maxId = tariffRepo.getMaxId();
        tariff.setTariffCode("TF-"+ (maxId == null ? 1 : maxId + 1));

        tariff.setCreatedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        tariff.setCreatedBy("System");
        tariff.setStatus(0);

        Tariff savedTarrif = tariffRepo.save(tariff);
        updateCache(savedTarrif);
        return savedTarrif;
    }

    @Override
    @CachePut(key="#tariffId")
    public Tariff update(Tariff tariff, Long tariffId) {
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
    public TariffSearchResult tariffList(String search, int pageNo, int pageSize, String sortBy, String sortOrder) {
        LOGGER.info("Fetching all Tariffs");
        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Tariff> pages = tariffRepo.findAllTariffs(search, pageable);
        return mapToTariffSearchResult(pageNo, pageSize, pages.getContent());
    }
    private TariffSearchResult mapToTariffSearchResult(int pageNo, int pageSize, List<Tariff> tariffs) {
        TariffSearchResult tariffSearchResult = new TariffSearchResult();
        Long totalPages = (long) (tariffs.size() % pageSize == 0 ? tariffs.size() / pageSize : tariffs.size() / pageSize+1);
        tariffSearchResult.setMetaData(HmsCommonUtil.getMetaData((long) tariffs.size(), totalPages, pageNo, pageSize));
        tariffSearchResult.setData(tariffs);

        return tariffSearchResult;
    }


    @Override
    @Cacheable(key="#tariffId")
    public Tariff findTariffById(Long tariffId) {
        LOGGER.info("Fetching tariff by id");
        Optional<Tariff> tariff = tariffRepo.findByTariffIdAndStatus(tariffId, 0);
        return tariff.orElseThrow(() ->
                new ResourceNotFoundException(String.format("Tariff not found with the given Id: %s", tariffId)));
    }

    @Transactional
    @Override
    @CacheEvict(key="#tariffId")
    public String deleteTariffById(Long tariffId) {
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

    private boolean isTariffExist(Long tariffId){
        return tariffRepo.findByTariffIdAndStatus(tariffId, 0).isPresent();
    }

    private void updateCache(Tariff tariff) {
        Cache cache = cacheManager.getCache("tariff");
        cache.put(tariff.getTariffId(), tariff);
    }
}