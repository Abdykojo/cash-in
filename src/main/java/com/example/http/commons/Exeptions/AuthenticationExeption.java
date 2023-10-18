package com.example.http.commons.Exeptions;

public class AuthenticationExeption extends RuntimeException{
    public AuthenticationExeption(String message) {
        super(message);
    }
}
