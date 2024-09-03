package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.DischargeFormat;
import com.hospital.management.entities.response.DischargeFormatSearchResult;

public interface DischargeFormatService {

    DischargeFormat saveDischargeFormat(DischargeFormat dischargeFormat);

    DischargeFormat updateDischargeFormat(DischargeFormat dischargeFormat,Long discFmtId);

    DischargeFormatSearchResult dischargeFormatList(String search, int pageNo, int pageSize, String sortBy, String sortOrder);

    DischargeFormat findDischargeFromatById(Long discFmtId);

    String deleteDischargeFormatById(Long discFmtId);
}
