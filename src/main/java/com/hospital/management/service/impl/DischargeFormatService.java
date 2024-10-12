package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.DischargeFormat;
import com.hospital.management.entities.search.DischargeFormatSearchResult;

import java.util.List;

public interface DischargeFormatService {

    DischargeFormatSearchResult dischargeFormatList(String search, int pageNo, int pageSize, String sortBy, String sortOrder);

    List<DischargeFormat> findDischargeFormatById(Long discFmtId);

    List<DischargeFormat> dischargeFormatListAll();

    DischargeFormat saveDischargeFormat(DischargeFormat dischargeFormat);

    DischargeFormat updateDischargeFormat(DischargeFormat dischargeFormat);

    DischargeFormat deleteDischargeFormatById(Long discFmtId);
}
