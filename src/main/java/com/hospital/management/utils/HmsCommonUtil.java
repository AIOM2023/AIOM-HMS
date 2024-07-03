package com.hospital.management.utils;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class HmsCommonUtil {

    public static OffsetDateTime getSystemDateInUTCFormat(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        return OffsetDateTime.parse(OffsetDateTime.now(ZoneId.of("UTC")).format(dateTimeFormatter));
    }
}
