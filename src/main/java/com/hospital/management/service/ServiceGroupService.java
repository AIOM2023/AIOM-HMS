package com.hospital.management.service;

import com.hospital.management.entities.commom.ServiceGroup;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ServiceGroupService {

    List<ServiceGroup> getAllServiceGroups();

    ServiceGroup findServiceGroupById(Long serviceGroupId);

    ServiceGroup saveServiceGroup(ServiceGroup serviceGroup);

    ServiceGroup updateServiceGroup(ServiceGroup serviceGroup, Long serviceGroupId);

    @Transactional
    String deleteServiceGroupById(Long serviceGroupId);
}
