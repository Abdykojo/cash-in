package com.example.http.service;
import com.example.http.request.PayRequest;
import com.example.http.response.BaseResponse;

public interface PayService {
    BaseResponse pay(PayRequest request);
}
