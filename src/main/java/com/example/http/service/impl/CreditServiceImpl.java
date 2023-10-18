package com.example.http.service.impl;

import com.example.http.commons.Exeptions.*;
import com.example.http.commons.RespMessage;
import com.example.http.commons.mapper.CreditsMapper;
import com.example.http.entity.Credit;
import com.example.http.entity.User;
import com.example.http.repository.CreditApplicationRepository;
import com.example.http.repository.CreditRepository;
import com.example.http.repository.UserRepository;
import com.example.http.request.CreditRequest;
import com.example.http.response.CreditResponse;
import com.example.http.service.ChartService;
import com.example.http.service.CreditService;
import jakarta.transaction.Transactional;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.http.commons.CreditStatus.COMMITTED;


@Service
@Slf4j
@RequiredArgsConstructor
public class CreditServiceImpl implements CreditService {
    private final CreditRepository repository;
    private final UserRepository userRepository;
    private final SaveService service;
    private final CreditApplicationRepository applicationRepository;
    private final ChartService chartService;

    @Override
    public CreditResponse getCredit(CreditRequest request) throws CashExeption {


        Credit credit = createCredit(request);
        System.out.println(credit.getId());
        chartService.calculation(credit.getId());

        return CreditResponse.builder()
                .id(credit.getId())
                .account(credit.getAccount())
                .payment(credit.getPayment())
                .percent(credit.getPercent())
                .code(RespMessage.CREDIT_GET.getMessage())
                .status(COMMITTED)
                .sum(credit.getSum())
                .build();


    }

    @Transactional(Transactional.TxType.REQUIRED)
    private Credit createCredit(CreditRequest request) throws CashExeption {

        try {

            log.info("CreditServiceImpls -> {}", request);

             userRepository.findByPhoneNumber(request.getAccount()).orElseThrow(()
                    -> new UserNotFoundExeption(RespMessage.USER_NOT_FOUND.getMessage()));

            if (request.getSum() < 2000 || request.getSum() > 250000) {
                throw new CreditsLimitsExeption(RespMessage.CREDIT_LIMITS.getMessage());
            }

            if (request.getTerm() < 2 || request.getTerm() > 36) {
                throw new CreditsLimitsExeption(RespMessage.CREDIT_LIMITS.getMessage());
            }

            Credit credit = new Credit();
            credit.setTerm(request.getTerm());
            credit.setPercent(applicationRepository.getBySum(request.getSum()));
            credit.setSum(request.getSum());
            double p = credit.getPercent() / 100 / 12;
            credit.setPayment(Math.ceil(request.getSum() * (p + p / (Math.pow(1 + p, request.getTerm()) - 1))));
            credit.setBalance(credit.getPayment() * request.getTerm());
            credit.setStatus(COMMITTED);
            credit.setAccount(request.getAccount());
            repository.save(credit);

            return credit;

        } catch (Exception e) {
            log.error("CreditServiceImpls -> {}", e.getLocalizedMessage());
            throw new CashExeption(RespMessage.SERVICE_NOT_RABOTAT.getMessage());
        }
    }

    @Override
    public CreditRequest findById(Long id) {

        return CreditsMapper.INSTANCE.toDto(repository.findById(id).orElseThrow(() -> new RuntimeException("")));
    }

    @Override
    public List<CreditResponse> findAll(CreditRequest request) throws CashExeption {
        try {

             userRepository.findByPhoneNumber(request.getAccount()).orElseThrow(()
                    -> new UserNotFoundExeption(RespMessage.USER_NOT_FOUND.getMessage()));
            List<CreditResponse> responses = new ArrayList<>();
            List<Credit> credit = repository.findCreditByAccount(request.getAccount());

            for (Credit resp : credit) {

                CreditResponse response = new CreditResponse();
                response.setId(resp.getId());
                response.setBalance(resp.getBalance());
                response.setSum(resp.getSum());
                response.setPayment(resp.getPayment());
                response.setPercent(resp.getPercent());
                response.setStatus(resp.getStatus());
                response.setAccount(resp.getAccount());
                responses.add(response);


            }
            return responses;

        } catch (CashExeption e) {
            log.error("CreditServiceImpls -> {}", e.getLocalizedMessage());
            throw new CashExeption(RespMessage.SERVICE_NOT_RABOTAT.getMessage());
        }
    }
}


