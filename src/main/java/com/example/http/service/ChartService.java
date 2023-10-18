package com.example.http.service;
import com.example.http.request.CreditRequest;
import com.example.http.request.ScheduleRequest;
import com.example.http.response.BaseResponse;

public interface ChartService {
    BaseResponse calculation(Long id);
}
