package com.example.http.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class CheckRespint {
    private String currency;
    private UUID qid;
    private String account;
    private String sender;
    private String name;
    private String dts;
    private String code;
    private String message;
}
