package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.DischargeFormat;
import com.hospital.management.entities.commom.DischargeSummary;

import java.util.List;

public interface DischargeFormatService {

    DischargeFormat saveDischargeFormat(DischargeFormat dischargeFormat);

    DischargeFormat updateDischargeFormat(DischargeFormat dischargeFormat,Long discFmtId);

    List<DischargeFormat> dischargeFormatList();

    DischargeFormat findDischargeFromatById(Long discFmtId);

    String deleteDischargeFormatById(Long discFmtId);
}
