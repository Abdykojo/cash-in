package com.example.http.commons.Exeptions;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BalanceExeption extends RuntimeException{
    public BalanceExeption(String message) {
        super(message);
    }
}
