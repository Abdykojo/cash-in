package com.example.http.request;

import com.example.http.entity.Credit;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement
public class ChartRequest {
    @XmlElement
    private Long id;
    @XmlElement
    private Credit creditId;
    @XmlElement
    private double firstBalance;
    @XmlElement
    private double payment;
    @XmlElement
    private double finalBalance;
    @XmlElement
    @DateTimeFormat(pattern = "dd.mm.yyyy")
    private LocalDateTime date;

}
