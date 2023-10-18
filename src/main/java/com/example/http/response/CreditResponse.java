package com.example.http.response;

import com.example.http.commons.CreditStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreditResponse {
    private Long id;
    @NonNull
    private double payment;
    private int sum;
    @NonNull
    private double balance;
    private double percent;
    @NonNull
    private CreditStatus status;
    private String code;
    private String account;

}
