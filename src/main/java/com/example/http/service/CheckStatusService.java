package com.example.http.service;
import com.example.http.request.CheckStatusRequest;
import com.example.http.response.BaseResponse;


public interface CheckStatusService  {
    BaseResponse checkStatus(CheckStatusRequest request);
}
