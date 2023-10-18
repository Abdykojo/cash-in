package com.example.http.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement
public class RegisterRequest {
    @XmlElement
    private String name;
    @XmlElement
    private String birthdate;
    @XmlElement
    private String phone;
    @XmlElement
    private String lastname;
    @XmlElement
    private String email;
    @XmlElement
    private String password;
}
