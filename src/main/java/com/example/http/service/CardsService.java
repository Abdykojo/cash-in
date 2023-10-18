package com.example.http.service;

import com.example.http.commons.Exeptions.CashExeption;
import com.example.http.request.CardsRequest;
import com.example.http.response.BaseResponse;
import com.example.http.response.CardsResponse;

import java.util.List;

public interface CardsService {
    BaseResponse createCard (CardsRequest request);

    List<CardsResponse> findAll(CardsRequest request) throws CashExeption;
}
