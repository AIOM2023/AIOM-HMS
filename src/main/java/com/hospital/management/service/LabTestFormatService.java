package com.hospital.management.service;


import com.hospital.management.entities.commom.LabTestFormat;

import java.util.List;

public interface LabTestFormatService {
    List<LabTestFormat> getAllTestFormats();

    LabTestFormat findTestFormatById(Long labTestFormatId);

    LabTestFormat saveTestFormat(LabTestFormat labTestFormat);

    LabTestFormat updateTestFormat(LabTestFormat labTestFormat, Long labTestFormatId);

    String deleteTestFormatById(Long labTestFormatId);
}
