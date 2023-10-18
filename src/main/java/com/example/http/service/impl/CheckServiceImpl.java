package com.example.http.service.impl;

import com.example.http.commons.Exeptions.CashExeption;
import com.example.http.commons.RespMessage;
import com.example.http.entity.User;
import com.example.http.request.CheckRequest;
import com.example.http.response.BaseResponse;
import com.example.http.service.CheckService;
import com.example.http.service.TypeServise;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CheckServiceImpl implements CheckService {

    private final SaveService service;
    private final TypeServise typeServise;

    @Override
    public BaseResponse checkPayment(CheckRequest request)  {

        log.info("CheckServiceImpls -> {}", request);

        User user = typeServise.findUser(request.getAccount(), request.getType()); //проверка счета

        try {

            service.checkSave(CheckRequest.builder()
                    .currency(request.getCurrency())
                    .account(request.getAccount())
                    .sender(request.getSender())
                    .status(RespMessage.OK.getStatus())
                    .date(String.valueOf(LocalDateTime.now()))
                    .qid(UUID.randomUUID())
                    .type(request.getType())
                    .build());

            return BaseResponse.builder().
                    code(RespMessage.OK.getStatus())
                    .message(RespMessage.OK.getMessage())
                    .qid(request.getQid())
                    .dts(String.valueOf(LocalDateTime.now()))
                    .name(user.getLast_name() + " " + user.getName())
                    .build();


        } catch (CashExeption e) {
            log.error("CheckServiceImpl -> {}", e.getLocalizedMessage());
            throw new CashExeption(RespMessage.SERVICE_NOT_RABOTAT.getMessage());
        }
    }

}

