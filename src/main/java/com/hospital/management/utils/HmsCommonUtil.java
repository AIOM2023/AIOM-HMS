package com.hospital.management.utils;

import com.hospital.management.entities.commom.MetaData;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;

public class HmsCommonUtil {

    public static OffsetDateTime getSystemDateInUTCFormat(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        return OffsetDateTime.parse(OffsetDateTime.now(ZoneId.of("UTC")).format(dateTimeFormatter));
    }

    public static String getCountryCodeFromName(String country) {
        return Arrays.stream(Locale.getISOCountries())
                .map((s) -> new Locale("", s))
                .filter((l) -> l.getDisplayCountry().equalsIgnoreCase(country))
                .findFirst()
                .map((l) -> l.getCountry().toUpperCase())
                .orElse("UNKNOWN");
    }

    public static MetaData getMetaData(Long totalRecords, Long totalPages, int currentPage, int pageSize){
        MetaData metaData = new MetaData();

        metaData.setTotalRecords(totalRecords);
        metaData.setTotalPages(totalPages);
        metaData.setCurrentPage(currentPage);
        metaData.setRecordsPerPage(pageSize);

        return metaData;
    }
}
