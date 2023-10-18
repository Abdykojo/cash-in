package com.example.http.commons.Exeptions;

import lombok.Getter;

public class QidExeption extends RuntimeException{
    public QidExeption(String message) {
        super(message);
    }
}
