package com.example.http.request;
import com.example.http.commons.CreditStatus;
import com.example.http.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlRootElement
public class CreditRequest {
    @XmlElement
    private Long id;
    @XmlElement
    @Min(2000)
    @Max(250000)
    private int sum;
    @XmlElement
    @Min(3)
    @Max(36)
    private int term;
    @XmlElement
    @NonNull
    private String account;
    @XmlElement
    private User userId;
    @XmlElement
    private double percent;
    @XmlElement
    private double payment;
    @XmlElement
    private double balance;
    @XmlElement
    @DateTimeFormat(pattern = "dd.mm.yyyy")
    private LocalDateTime date;
    @XmlElement
    private CreditStatus status;
}
