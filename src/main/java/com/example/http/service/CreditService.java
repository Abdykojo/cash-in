package com.example.http.service;

import com.example.http.entity.Credit;
import com.example.http.request.CreditRequest;
import com.example.http.response.CreditResponse;

import java.util.List;

public interface CreditService {

    CreditResponse getCredit (CreditRequest request);

    CreditRequest findById(Long id);

    List<CreditResponse> findAll(CreditRequest request);
}
