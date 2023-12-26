package com.hospital.management.service;

import com.hospital.management.entities.commom.CaptionDay;

import java.util.List;

public interface CaptionDayService {
    void save(CaptionDay captionDay);

    void update(CaptionDay captionDay);

    List<CaptionDay> captionDayList();
}
