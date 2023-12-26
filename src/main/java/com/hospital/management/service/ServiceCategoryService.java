package com.hospital.management.service;


import com.hospital.management.entities.commom.ServiceCategory;

import java.util.List;

public interface ServiceCategoryService {

    void save(ServiceCategory serviceCategory);

    void update(ServiceCategory serviceCategory);

    List<ServiceCategory> serviceCategoryList();
}
