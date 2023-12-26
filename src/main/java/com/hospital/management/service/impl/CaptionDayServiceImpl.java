package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.CaptionDay;
import com.hospital.management.repositary.CaptionDayRepo;
import com.hospital.management.service.CaptionDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaptionDayServiceImpl implements CaptionDayService {

    @Autowired
    CaptionDayRepo captionDayRepo;

    @Override
    public void save(CaptionDay captionDay) {
        captionDayRepo.save(captionDay);
    }

    @Override
    public void update(CaptionDay captionDay) {
        captionDayRepo.save(captionDay);
    }

    @Override
    public List<CaptionDay> captionDayList() {
        return captionDayRepo.findAll();
    }
}
