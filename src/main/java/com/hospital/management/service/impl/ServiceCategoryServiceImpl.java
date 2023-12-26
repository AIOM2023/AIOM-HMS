package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.ServiceCategory;
import com.hospital.management.repositary.ServiceCategoryRepo;
import com.hospital.management.service.ServiceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCategoryServiceImpl implements ServiceCategoryService {

    @Autowired
    ServiceCategoryRepo serviceCategoryRepo;
    @Override
    public void save(ServiceCategory serviceCategory) {
        serviceCategoryRepo.save(serviceCategory);
    }

    @Override
    public void update(ServiceCategory serviceCategory) {
        serviceCategoryRepo.save(serviceCategory);
    }

    @Override
    public List<ServiceCategory> serviceCategoryList() {
        return serviceCategoryRepo.findAll();
    }
}
