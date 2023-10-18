package com.example.http.request;
import com.example.http.entity.Credit;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.xml.bind.annotation.XmlElement;
import java.time.LocalDateTime;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ScheduleRequest {

    @NonNull
    @XmlElement
    private Long creditId;
    @NonNull
    @XmlElement
    private double payment;
    @XmlElement
    private String account;
    @NonNull
    @XmlElement
    private String currency;
    @XmlElement
    @DateTimeFormat(pattern = "dd.mm.yyyy")
    private LocalDateTime date;
    private double firstBalance;
    private double finalBalance;


}
