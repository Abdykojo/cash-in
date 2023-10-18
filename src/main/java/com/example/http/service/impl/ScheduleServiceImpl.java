package com.example.http.service.impl;

import com.example.http.commons.CreditStatus;
import com.example.http.commons.Exeptions.BalanceExeption;
import com.example.http.commons.Exeptions.CashExeption;
import com.example.http.commons.Exeptions.CreditExeption;
import com.example.http.commons.RespMessage;
import com.example.http.entity.*;
import com.example.http.repository.*;
import com.example.http.request.ScheduleRequest;
import com.example.http.response.ScheduleResponse;
import com.example.http.service.ScheduleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository repository;
    private final CreditRepository creditRepository;
    private final CourseRepository courseRepository;
    private final CardsRepository cardsRepository;
    private final SaveService service;

    @Transactional(Transactional.TxType.REQUIRED)
    public ScheduleResponse creditPayment(ScheduleRequest request) throws CashExeption {
        try {
            log.info("ScheduleServiceImpls -> {}", request);

            Credit credit = creditRepository.getById(request.getCreditId());
            Long id = credit.getId();

            if (repository.findSchedule(id).isPresent()) {

                Schedule schedule = repository.getSchedulesById(id);
                ScheduleRequest schedule1 = ScheduleRequest.builder()
                        .creditId(schedule.getCreditId())
                        .payment(request.getPayment())
                        .firstBalance(schedule.getFinalBalance())
                        .finalBalance(schedule.getFinalBalance() - request.getPayment())
                        .date(LocalDateTime.now())
                        .currency(request.getCurrency())
                        .build();

                if (schedule1.getFinalBalance() < 0) {
                    throw new CreditExeption(RespMessage.LARGE_SUM.getMessage());
                }

                cardsDown(request);
                service.scheduleSave(schedule1);

                credit.setBalance(schedule1.getFinalBalance());
                creditRepository.save(credit);

                return ScheduleResponse.builder()
                        .payment(request.getPayment())
                        .balance(schedule1.getFinalBalance())
                        .status(CreditStatus.COMMITTED)
                        .build();

            }

            cardsDown(request);
            service.scheduleSave(ScheduleRequest.builder()
                    .creditId(credit.getId())
                    .payment(request.getPayment())
                    .firstBalance(credit.getBalance())
                    .finalBalance(credit.getBalance() - request.getPayment())
                    .date(LocalDateTime.now())
                    .currency(request.getCurrency())
                    .build());


            return ScheduleResponse.builder()
                    .payment(request.getPayment())
                    .balance(credit.getBalance() - request.getPayment())
                    .status(CreditStatus.COMMITTED)
                    .build();

        } catch (CashExeption e) {
            log.error("ScheduleServiceImpls -> {}", e.getLocalizedMessage());
            throw new CashExeption(RespMessage.SERVICE_NOT_RABOTAT.getMessage());
        }
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void cardsDown(ScheduleRequest request) {

        Course sell = courseRepository.getCourseByCurrency(request.getCurrency());
        Credit credit = creditRepository.getById(request.getCreditId());
        Cards balance = cardsRepository.getCardsByAccountAndMainTrue(credit.getAccount());
        Course buy = courseRepository.getCourseByCurrency(balance.getCurrency());

        Double sum = request.getPayment() / buy.getCourseSum();
        Double finalBalance = balance.getBalance() - (sum * sell.getCourseSum());

        if (finalBalance < 0) {
            throw new BalanceExeption(RespMessage.NOT_MONEY.getMessage());
        }

        balance.setBalance(finalBalance);
        cardsRepository.save(balance);
    }


}
