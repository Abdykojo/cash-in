package com.example.http.service.impl;

import com.example.http.commons.Exeptions.BadRequestExeption;
import com.example.http.commons.Exeptions.BalanceExeption;
import com.example.http.commons.Exeptions.CashExeption;
import com.example.http.commons.Exeptions.QidExeption;
import com.example.http.commons.RespMessage;
import com.example.http.entity.*;
import com.example.http.repository.*;
import com.example.http.request.PayRequest;
import com.example.http.response.BaseResponse;
import com.example.http.service.PayService;
import com.example.http.service.TypeServise;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class PayServiceImpl implements PayService {

    private final CheckRepository checkRepository;
    private final PayRepository repository;
    private final CardsRepository cardsRepository;
    private final CourseRepository courseRepository;
    private final SaveService save;
    private final TypeServise typeServise;

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public BaseResponse pay(PayRequest request) {

        log.info("PayServiceImpls -> {}", request);

        Check check = checkRepository.getByQid(request.getQid()).orElseThrow(() ->
                new BadRequestExeption(RespMessage.BAD_REQUEST.getMessage()));

        var res = repository.findByQid(request.getQid());

        if (res.isEmpty()) {

            try {
                PayRequest payRequest = new PayRequest();
                payRequest.setQid(request.getQid());
                payRequest.setAccount(check.getAccount());
                payRequest.setDate(String.valueOf(LocalDateTime.now()));
                payRequest.setSum(request.getSum());
                payRequest.setCurrency(request.getCurrency());
                payRequest.setStatus(RespMessage.FOR_PROCESSING.getStatus());
                save.paySave(payRequest);
                cardsDown(request);
                cardsUp(request);

                return
                        BaseResponse.builder()
                                .code(RespMessage.FOR_PROCESSING.getStatus())
                                .message(RespMessage.FOR_PROCESSING.getMessage())
                                .dts(String.valueOf(LocalDateTime.now()))
                                .qid(request.getQid())
                                .build();

            } catch (Exception e) {
                log.error("PayServiceImpls -> {}", e.getLocalizedMessage());
                throw new CashExeption(RespMessage.SERVICE_NOT_RABOTAT.getMessage());

            }
        }

        if (repository.getByQid(request.getQid()).getStatus().equals(RespMessage.FOR_PROCESSING.getStatus())) {
            Pay pay = repository.getByQid(request.getQid());
            pay.setStatus(RespMessage.COMMITTED.getStatus());
            repository.save(pay);

            return
                    BaseResponse.builder()
                            .code(RespMessage.COMMITTED.getStatus())
                            .message(RespMessage.COMMITTED.getMessage())
                            .dts(String.valueOf(LocalDateTime.now()))
                            .qid(request.getQid())
                            .build();

        }

        log.error("PayServiceImpls -> {}", RespMessage.QID_IS_PRESENT.getMessage());
        throw new QidExeption(RespMessage.QID_IS_PRESENT.getMessage());
    }


    @Transactional(Transactional.TxType.REQUIRED)
    public void cardsUp(PayRequest request) {

        Course sell = courseRepository.getCourseByCurrency(request.getCurrency());
        Cards cashInBalance = cardsRepository.getCardsByAccountAndMainTrue(request.getAccount());
        Course buy = courseRepository.getCourseByCurrency(cashInBalance.getCurrency());
        Double sum = request.getSum() / buy.getCourseSum();
        Double finalBalance = cashInBalance.getBalance() + (sum * sell.getCourseSum());
        cashInBalance.setBalance(finalBalance);
        cardsRepository.save(cashInBalance);

    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void cardsDown(PayRequest request) throws BalanceExeption {

        Course sell = courseRepository.getCourseByCurrency(request.getCurrency());
        Cards balance = cardsRepository.getCardsByAccountAndMainTrue(request.getAccount());
        Course buy = courseRepository.getCourseByCurrency(balance.getCurrency());
        Double sum = request.getSum() / buy.getCourseSum();
        Double finalBalance = balance.getBalance() - (sum * sell.getCourseSum());

        if (finalBalance < 0) {
            throw new BalanceExeption(RespMessage.NOT_MONEY.getMessage());
        }
        balance.setBalance(finalBalance);
        cardsRepository.save(balance);
    }

}
