package com.example.http.request;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlRootElement
public class PayRequest {
    @NonNull
    @XmlElement
    private Double sum;
    @NonNull
    @XmlElement
    private String currency;
    @NonNull
    @XmlElement
    private UUID qid;
    @NonNull
    @XmlElement
    private String account;
    @NonNull
    @XmlElement
    private String date;
    @XmlElement
    private String status;
    @XmlElement
    private String type;

}
