package com.example.http.service.impl;

import com.example.http.commons.mapper.*;
import com.example.http.repository.*;
import com.example.http.request.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveService { // generics
    private final PayRepository repository;
    private final StatusRepository statusRepository;
    private final CheckStatusMapper mapper = CheckStatusMapper.INSTANCE;
    private final PayMapper payMapper = PayMapper.INSTANCE;
    private final CheckRepository checkRepository;
    private final CheckMapper checkMapper = CheckMapper.INSTANCE;
    private final CreditRepository creditRepository;
    private final CreditsMapper creditsMapper = CreditsMapper.INSTANCE;
    private final ScheduleMapper scheduleMapper = ScheduleMapper.INSTANCE;
    private final ScheduleRepository scheduleRepository;
    private final ChartRepository chartRepository;
    private final ChartMapper chartMapper = ChartMapper.INSTANCE;
    private final CardsMapper cardsMapper = CardsMapper.INSTANCE;
    private final CardsRepository cardsRepository;


    public CheckRequest checkSave(CheckRequest checkRequest) {
        return checkMapper.toDto(checkRepository.save(checkMapper.toEntity(checkRequest)));
    }

    public CheckStatusRequest statusSave(CheckStatusRequest request) {
        return mapper.toDto(statusRepository.save(mapper.toEntity(request)));
    }

    public PayRequest paySave(PayRequest payRequest) {
        return payMapper.toDto(repository.save(payMapper.toEntity(payRequest)));
    }

    public CreditRequest creditSave(CreditRequest creditRequest) {
        return creditsMapper.toDto(creditRepository.save(creditsMapper.toEntity(creditRequest)));
    }
    public ScheduleRequest scheduleSave(ScheduleRequest scheduleRequest) {
        return scheduleMapper.toDto(scheduleRepository.save(scheduleMapper.toEntity(scheduleRequest)));
    }
    public ChartRequest chartSave(ChartRequest chartRequest) {
        return chartMapper.toDto(chartRepository.save(chartMapper.toEntity(chartRequest)));
    }
    public CardsRequest cardSave(CardsRequest cardsRequest) {
        return cardsMapper.toDto(cardsRepository.save(cardsMapper.toEntity(cardsRequest)));
    }

}
