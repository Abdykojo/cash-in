package com.example.http.service.impl;

import com.example.http.commons.Exeptions.CashExeption;
import com.example.http.commons.Exeptions.UserNotFoundExeption;
import com.example.http.commons.RespMessage;
import com.example.http.entity.Cards;
import com.example.http.repository.CardsRepository;
import com.example.http.repository.UserRepository;
import com.example.http.request.CardsRequest;
import com.example.http.response.BaseResponse;
import com.example.http.response.CardsResponse;
import com.example.http.service.CardsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.cache.CacheException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CardsServiceImpl implements CardsService {

    private final UserRepository userRepository;
    private final CardsRepository repository;

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public BaseResponse createCard(CardsRequest request) {
        System.out.println(request.getAccount());

         userRepository.findByPhoneNumber(request.getAccount()).orElseThrow(()
                -> new UserNotFoundExeption(RespMessage.USER_NOT_FOUND.getMessage()));
        try {

            int a;
            boolean isCardExist = true;
            while (isCardExist) {
                a = (int) Math.ceil(Math.random() * (1000000 - 100000 + 1) + 100000);
                isCardExist = repository.existsCardsByNumber(a);
            }

            if (repository.findCardsByAccountAndMainTrue(request.getAccount()).isEmpty()) {
                repository.save(Cards.builder()
                        .currency(request.getCurrency())
                        .balance(0.0)
                        .date(LocalDateTime.now())
                        .main(true)
                        .account(request.getAccount())
                        .number(a)
                        .build());
            } else {
                repository.save(Cards.builder()
                        .currency(request.getCurrency())
                        .balance(0.0)
                        .date(LocalDateTime.now())
                        .main(false)
                        .account(request.getAccount())
                        .number(a)
                        .build());
            }


            return BaseResponse.builder()
                    .message(RespMessage.CARDS.getMessage())
                    .code(RespMessage.CARDS.getStatus())
                    .build();

        } catch (CacheException e) {
            throw new CashExeption(RespMessage.SERVICE_NOT_RABOTAT.getMessage());

        }
    }
    @Override
    public List<CardsResponse> findAll(CardsRequest request) throws CashExeption {

        try {

            userRepository.findByPhoneNumber(request.getAccount()).orElseThrow(()
                    -> new UserNotFoundExeption(RespMessage.USER_NOT_FOUND.getMessage()));
            List<CardsResponse> responses = new ArrayList<>();
            List<Cards> cards = repository.getCardsByAccount(request.getAccount());

            for (Cards resp : cards) {

                CardsResponse response = new CardsResponse();
                response.setAccount(resp.getAccount());
                response.setBalance(resp.getBalance());
                response.setCurrency(resp.getCurrency());
                response.setNumber(resp.getNumber());
                response.setMain(resp.isMain());
                responses.add(response);

            }
            return responses;

        } catch (CashExeption e) {
            log.error("CreditServiceImpls -> {}", e.getLocalizedMessage());
            throw new CashExeption(RespMessage.SERVICE_NOT_RABOTAT.getMessage());
        }
    }
}
