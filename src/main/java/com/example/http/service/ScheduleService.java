package com.example.http.service;
import com.example.http.request.ScheduleRequest;
import com.example.http.response.CreditResponse;
import com.example.http.response.ScheduleResponse;

import java.util.List;

public interface ScheduleService {
    ScheduleResponse creditPayment(ScheduleRequest request);


}
