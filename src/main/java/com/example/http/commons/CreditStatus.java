package com.example.http.commons;

import lombok.Getter;


@Getter
public enum CreditStatus {
    COMMITTED,

    FAILED,
    PROCESSING;
}
