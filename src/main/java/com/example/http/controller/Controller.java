package com.example.http.controller;
import com.example.http.request.*;
import com.example.http.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class Controller {

    private final CheckService checkService;
    private final PayService service;
    private final CheckStatusService statusService;
    private final CardsService cardsService;
    private final MydataService mydataService;

    @PostMapping(value = "/check",
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> sendCheck(@RequestBody CheckRequest request)  {
            return ResponseEntity.accepted().body(checkService.checkPayment(request));

    }

    @PostMapping(value = "/pay",
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> sendPay(@RequestBody PayRequest request) {
            return ResponseEntity.accepted().body(service.pay(request));
           }

    @PostMapping(value = "/status",
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> sendStatus(@RequestBody CheckStatusRequest request) {
        return ResponseEntity.accepted().body(statusService.checkStatus(request));
    }

    @PostMapping(value = "/cards",
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> sendStatus(@RequestBody CardsRequest request) {
        return ResponseEntity.accepted().body(cardsService.createCard(request));
    }
    @PostMapping(value = "/cards/findAll",
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> findAll(@RequestBody CardsRequest request) {
        return ResponseEntity.accepted().body(cardsService.findAll(request));
    }
    @PostMapping(value = "/json",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> json(String name)  {
        return ResponseEntity.ok(mydataService.create(name));

    }
}
