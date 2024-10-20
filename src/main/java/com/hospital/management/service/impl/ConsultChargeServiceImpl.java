package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.BillingHead;
import com.hospital.management.entities.commom.ConsultCharge;
import com.hospital.management.entities.commom.Tariff;
import com.hospital.management.entities.response.ConsultChargesSearchResult;
import com.hospital.management.exceptions.HmsBusinessException;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.payload.ErrorResponse;
import com.hospital.management.repositary.BillingHeadRepo;
import com.hospital.management.repositary.ConsultChargeRepo;
import com.hospital.management.repositary.TariffRepo;
import com.hospital.management.repositary.WardChargeRepo;
import com.hospital.management.service.ConsultChargeService;
import com.hospital.management.utils.HmsCommonUtil;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ConsultChargeServiceImpl implements ConsultChargeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsultChargeService.class);
    private static final String CONSULT_CHARGE_NOT_FOUND = "ConsultCharge not found with the given Id: %s";

    @Autowired
    ConsultChargeRepo consultChargeRepo;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private TariffRepo tariffRepo;

    @Autowired
    private BillingHeadRepo billingHeadRepo;

    @Autowired
    private WardChargeRepo wardRepo;

    static List<String> cacheNames;

    static {
        cacheNames = List.of("tariff", "billing", "consultant");
    }

    @Override
    public ConsultCharge saveConsultCharge(ConsultCharge consultCharge) {
        LOGGER.info("Creating a new consultCharge");

        Long maxId = consultChargeRepo.getMaxId();
        consultCharge.setConsultCode("CON-"+(maxId == null ? 1 : maxId+1));
        consultCharge.setCreatedBy("System");
        consultCharge.setCreatedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        consultCharge.setStatus(0);

        consultCharge.getWardCharges().forEach(ward -> {
            Long maxWardId = wardRepo.getMaxId();
            ward.setWardCode("WD-"+(maxWardId == null ? 1 : maxWardId+1));
            ward.setCreatedBy("System");
            ward.setCreatedDate(HmsCommonUtil.getSystemDateInUTCFormat());
            ward.setStatus(0);
        });
        return consultChargeRepo.save(consultCharge);
    }

    @Override
    public ConsultCharge updateConsultCharge(ConsultCharge consultCharge, Long consultId) {
        LOGGER.info("Updating an existing ConsultCharge");
        if(!isConsultChargeExist(consultId)) {
            LOGGER.error("update() - ConsultCharge not found with the given Id: {} ", consultId);
            throw new ResourceNotFoundException(String.format(CONSULT_CHARGE_NOT_FOUND, consultId));
        }
        consultCharge.setModifiedBy("System");
        consultCharge.setModifiedDate(HmsCommonUtil.getSystemDateInUTCFormat());

        consultCharge.getWardCharges().forEach(ward -> {
            ward.setModifiedBy("System");
            ward.setModifiedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        });

        return consultChargeRepo.save(consultCharge);
    }

    @Override
    public ConsultCharge findConsultChargeById(Long consultId) {
        LOGGER.info("Fetching ConsultCharge by id");
        ConsultCharge consultCharge = consultChargeRepo.findByConsultIdAndStatus(consultId, 0).orElse(null);
        if(Objects.nonNull(consultCharge)) {
            Cache tariffCache = cacheManager.getCache("tariff");
            Tariff tariff = (Tariff) tariffCache.get(consultCharge.getTariffId()).get();
            if(tariff == null){
                LOGGER.info("Fetched tariffName from DB");
                tariff =  tariffRepo.findByTariffIdAndStatus(consultCharge.getTariffId(), 0).get();
            }
            consultCharge.setTariffName(tariff.getTariffName());

            Cache billingCache = cacheManager.getCache("billing");
            BillingHead billingHead = (BillingHead) billingCache.get(consultCharge.getBillingHeadId()).get();
            if(billingHead == null){
                LOGGER.info("Fetched billingHeadName from DB");
                billingHead =  billingHeadRepo.findByBillingHeadIdAndStatus(consultCharge.getBillingHeadId(), 0).get();
            }
            consultCharge.setBillingHeadName(billingHead.getBillingHeadName());
        } else {
            throw new ResourceNotFoundException(String.format(CONSULT_CHARGE_NOT_FOUND, consultId));
        }
        return consultCharge;
    }

    @Transactional
    @Override
    public String deleteConsultChargeById(Long consultId) {
        if(!isConsultChargeExist(consultId)) {
            LOGGER.error("deleteConsultChargeById() - ConsultCharge not found with the given Id: {} ", consultId);
            throw new ResourceNotFoundException(String.format(CONSULT_CHARGE_NOT_FOUND, consultId));
        }
        try{
            consultChargeRepo.deleteConsultChargeById(consultId);
        } catch (Exception ex){
            LOGGER.error("deleteConsultChargeById() - Unable to delete ConsultCharge with the given Id: {} ", consultId);
            ErrorResponse errorResponse = new ErrorResponse("500", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new HmsBusinessException(String.format("Unable to delete ConsultCharge with the given Id: %s", consultId), errorResponse);
        }

        return "ConsultCharge deleted successfully!";
    }
    @Override
    public ConsultChargesSearchResult getAllConsultCharges(String search, int pageNo, int pageSize, String sortBy, String sortOrder) {
        LOGGER.info("Fetching all ConsultCharges");
        int actualPage = pageNo - 1; // Pages in Spring Data start from 0
        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
        Pageable pageable = PageRequest.of(actualPage, pageSize,sort);
        Page<ConsultCharge> pages = consultChargeRepo.findAllConsultCharges(search, pageable);

        return mapToConsultChargesSearchResult(pageNo, pageSize, pages);
    }

    private boolean isConsultChargeExist(Long consultId){
        return consultChargeRepo.findByConsultIdAndStatus(consultId, 0).isPresent();
    }

    private ConsultChargesSearchResult mapToConsultChargesSearchResult(int pageNo, int pageSize, Page<ConsultCharge> pages) {
        ConsultChargesSearchResult consultChargesSearchResult = new ConsultChargesSearchResult();
        consultChargesSearchResult.setMetaData(HmsCommonUtil.getMetaData(pages.getTotalElements(), (long) pages.getTotalPages(), pageNo, pageSize));
        List<ConsultCharge> consultCharges = pages.getContent();
        Cache tariffCache = cacheManager.getCache("tariff");
        Cache billingCache = cacheManager.getCache("billing");
        List<Long> tariffIds = new ArrayList<>();
        List<Long> billingIds = new ArrayList<>();

        List<ConsultCharge> consultChargeList = consultCharges.stream()
                .peek(consult -> {
                    Tariff tariff = (Tariff) tariffCache.get(consult.getTariffId()).get();
                    if(Objects.nonNull(tariff)) {
                        consult.setTariffName(tariff.getTariffName());
                    } else {
                        tariffIds.add(consult.getTariffId());
                    }

                    BillingHead billingHead = (BillingHead) billingCache.get(consult.getBillingHeadId()).get();
                    if(Objects.nonNull(tariff)) {
                        consult.setBillingHeadName(billingHead.getBillingHeadName());
                    } else {
                        billingIds.add(consult.getBillingHeadId());
                    }
                }).toList();

        if(!tariffIds.isEmpty()){
            List<Tariff> dbTariffList = tariffRepo.findByTariffIdIn(tariffIds);
            dbTariffList.forEach(tariff -> {
                consultChargeList.parallelStream()
                        .filter(con -> tariff.getTariffId().equals(con.getTariffId()))
                        .findFirst()
                        .get().setTariffName(tariff.getTariffName());
            });
        }

        if(!billingIds.isEmpty()){
            List<BillingHead> dbBillingList = billingHeadRepo.findByBillingHeadIdIn(billingIds);
            dbBillingList.forEach(billing -> {
                consultChargeList.parallelStream()
                        .filter(con -> billing.getBillingHeadId().equals(con.getBillingHeadId()))
                        .findFirst()
                        .get().setBillingHeadName(billing.getBillingHeadName());
            });
        }
        consultChargesSearchResult.setData(consultChargeList);
        return consultChargesSearchResult;
    }

}
