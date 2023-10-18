package com.example.http.controller;

import com.example.http.request.CreditRequest;
import com.example.http.request.ScheduleRequest;
import com.example.http.service.ChartService;
import com.example.http.service.CreditService;
import com.example.http.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/credit")
@RequiredArgsConstructor
public class CreditController {

    private final CreditService creditService;
    private final ScheduleService scheduleService;
    private final ChartService chartService;

    @PostMapping(value = "/get",
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> credit(@RequestBody CreditRequest creditRequest) {
        return ResponseEntity.ok(creditService.getCredit(creditRequest));

    }
    @PostMapping(value = "/payCredit",
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> payCredit(@RequestBody ScheduleRequest scheduleRequest) {
        return ResponseEntity.ok(scheduleService.creditPayment(scheduleRequest));

    }
    @PostMapping(value = "/findById",
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> findById(@RequestBody Long id) {
        return ResponseEntity.ok(creditService.findById(id));

    }
    @PostMapping(value = "/findAll",
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> findAll(@RequestBody CreditRequest request) {
        return ResponseEntity.ok(creditService.findAll(request));

    }
//    @PostMapping(value = "/calculate",
//            consumes = MediaType.APPLICATION_XML_VALUE,
//            produces = MediaType.APPLICATION_XML_VALUE)
//    public ResponseEntity<?> calculate(@RequestBody ScheduleRequest request) {
//        return ResponseEntity.ok(chartService.calculation(request));
//
//    }
}
