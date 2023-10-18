package com.example.http.controller;

import com.example.http.auth.config.AuthenticationService;
import com.example.http.request.AuthenticationRequest;
import com.example.http.request.RegisterRequest;
import com.example.http.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    @PostMapping(value = "/register",
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request) {

        return ResponseEntity.ok(service.register(request));

    }
    @PostMapping(value = "/authenticate",
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request ) {

        return ResponseEntity.ok(service.authenticate(request));
    }
}
