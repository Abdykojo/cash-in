package com.example.http.commons.Exeptions;

public class BadRequestExeption extends RuntimeException {
    public BadRequestExeption(String message) {
        super(message);
    }
}
