package com.example.http.response;

import com.example.http.commons.CreditStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScheduleResponse {
    @NonNull
    private double payment;
    @NonNull
    private double balance;
    @NonNull
    private CreditStatus status;
}
