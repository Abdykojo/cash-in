package com.example.http.request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class AuthenticationRequest {
    @XmlElement
    private String email;
    @XmlElement
    private String password;
}
