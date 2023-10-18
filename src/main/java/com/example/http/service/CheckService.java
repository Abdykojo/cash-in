package com.example.http.service;
import com.example.http.request.CheckRequest;
import com.example.http.response.BaseResponse;
import com.example.http.response.CheckRespint;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.xml.bind.JAXBException;
import java.io.IOException;


public interface CheckService {

    BaseResponse checkPayment(CheckRequest request) ;

}
