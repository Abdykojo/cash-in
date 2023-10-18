package com.example.http.service.impl;

import com.example.http.commons.Exeptions.CashExeption;
import com.example.http.commons.RespMessage;
import com.example.http.entity.Chart;
import com.example.http.entity.Credit;
import com.example.http.repository.ChartRepository;
import com.example.http.repository.CreditRepository;
import com.example.http.request.ChartRequest;
import com.example.http.response.BaseResponse;
import com.example.http.service.ChartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@Slf4j
@RequiredArgsConstructor
public class ChartServiceImpl implements ChartService {
    private final CreditRepository repository;
    private final ChartRepository chartRepository;
    private final SaveService service;

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public BaseResponse calculation(Long id) throws CashExeption {
        try {

            log.info("ChartServiceImpl -> {}", id);

            Credit credit = repository.getById(id);
            service.chartSave(ChartRequest.builder()
                    .creditId(credit)
                    .payment(credit.getPayment())
                    .firstBalance(credit.getBalance())
                    .finalBalance(credit.getBalance())
                    .date(LocalDateTime.now())
                    .build());

            for (int i = 0; i < credit.getTerm(); i++) {

                Long id2 = credit.getId();
                double finalBalance = chartRepository.findBid(id2);
                Chart chart2 = chartRepository.findChart(id2, finalBalance);
                Chart ccc = new Chart();
                ccc.setCreditId(chart2.getCreditId());
                ccc.setPayment(chart2.getPayment());
                ccc.setFirstBalance(chart2.getFinalBalance());
                ccc.setFinalBalance(ccc.getFirstBalance() - chart2.getPayment());
                ccc.setDate(LocalDateTime.now());
                chartRepository.save(ccc);

            }
            return BaseResponse.builder()
                    .code(RespMessage.GOOD.getStatus())
                    .message(RespMessage.GOOD.getMessage())
                    .build();

        } catch (CashExeption e) {

            log.error("ChartServiceImpl -> {}", e.getLocalizedMessage());
            throw new CashExeption(RespMessage.SERVICE_NOT_RABOTAT.getMessage());
        }
    }

}