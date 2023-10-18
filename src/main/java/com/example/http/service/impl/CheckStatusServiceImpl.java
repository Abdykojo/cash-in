package com.example.http.service.impl;

import com.example.http.commons.Exeptions.CashExeption;
import com.example.http.commons.RespMessage;
import com.example.http.repository.PayRepository;
import com.example.http.request.CheckStatusRequest;
import com.example.http.response.BaseResponse;
import com.example.http.service.CheckStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor

public class CheckStatusServiceImpl implements CheckStatusService {

    private final PayRepository payRepository;
    private final SaveService save;

    @Override
    public BaseResponse checkStatus(CheckStatusRequest request) {

        log.info("CheckStatusServiceImpls -> {}", request);

        try {

            CheckStatusRequest statusRequest = new CheckStatusRequest();

            if (payRepository.getByQid(request.getQid()).getStatus().equals(RespMessage.COMMITTED.getStatus())) {

                statusRequest.setDate(String.valueOf(LocalDateTime.now()));
                statusRequest.setQid(request.getQid());
                statusRequest.setStatus(RespMessage.COMMITTED.getStatus());
                save.statusSave(statusRequest);

                return
                        BaseResponse.builder()
                                .code(RespMessage.COMMITTED.getStatus())
                                .message(RespMessage.COMMITTED.getMessage())
                                .dts(String.valueOf(LocalDateTime.now()))
                                .qid(request.getQid())
                                .build();
            }
            statusRequest.setDate(String.valueOf(LocalDateTime.now()));
            statusRequest.setQid(request.getQid());
            statusRequest.setStatus(RespMessage.FAILED.getStatus());
            save.statusSave(statusRequest);
            return BaseResponse.builder()

                    .code(RespMessage.FAILED.getStatus())
                    .message(RespMessage.FAILED.getMessage())
                    .dts(String.valueOf(LocalDateTime.now()))
                    .qid(request.getQid())
                    .build();

        } catch (CashExeption e) {

            log.error("CheckStatusServiceImpls -> {}", e.getLocalizedMessage());
            throw new CashExeption(RespMessage.SERVICE_NOT_RABOTAT.getMessage());
        }

    }

}


