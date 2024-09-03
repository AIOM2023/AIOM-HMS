package com.hospital.management.service;

import com.hospital.management.entities.commom.ServiceGroup;
import com.hospital.management.entities.response.ServiceGroupSearchResult;
import org.springframework.transaction.annotation.Transactional;

public interface ServiceGroupService {

    ServiceGroupSearchResult getAllServiceGroups(String search, int pageNo, int pageSize, String sortBy, String sortOrder);

    ServiceGroup findServiceGroupById(Long serviceGroupId);

    ServiceGroup saveServiceGroup(ServiceGroup serviceGroup);

    ServiceGroup updateServiceGroup(ServiceGroup serviceGroup, Long serviceGroupId);

    @Transactional
    String deleteServiceGroupById(Long serviceGroupId);
}
