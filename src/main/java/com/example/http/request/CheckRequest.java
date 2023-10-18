package com.example.http.request;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;

@Getter
@ToString
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlRootElement
public class CheckRequest {
    @NonNull
    @XmlElement
    private String currency;
    @NonNull
    @XmlElement
    private String account;
    @NonNull
    @XmlElement
    private String sender;
    private String status;
    private UUID qid;
    @XmlElement
    private String date;
    @NonNull
    @XmlElement
    private String type;

}
