package com.hospital.management.config;

import com.hospital.management.entities.commom.BillingHead;
import com.hospital.management.entities.commom.Tariff;
import com.hospital.management.repositary.BillingHeadRepo;
import com.hospital.management.repositary.TariffRepo;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class HmsCacheConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(HmsCacheConfig.class);

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private TariffRepo tariffRepo;

    @Autowired
    private BillingHeadRepo billingHeadRepo;

    /*@Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager();
    }*/

    @PostConstruct
    public void loadCache() {
        Cache tariffCache = cacheManager.getCache("tariff");
        for(Tariff tariff : tariffRepo.findByStatus(0)) {
            tariffCache.put(tariff.getTariffId(), tariff);
        }

        Cache billingCache = cacheManager.getCache("billing");
        for(BillingHead billingHead : billingHeadRepo.findByStatus(0)) {
            billingCache.put(billingHead.getBillingHeadId(), billingHead);
        }

       LOGGER.info("Loaded Data from cache......");
    }
}
