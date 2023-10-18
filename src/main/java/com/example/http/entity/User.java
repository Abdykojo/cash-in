package com.example.http.entity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.xml.bind.annotation.XmlElement;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_user")
public class User implements UserDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement
    private Long id;
    @Column(name = "name")
    @XmlElement
    private String name;
    @Column(name = "last_name")
    @XmlElement
    private String last_name;
    @Column(name = "phone_number")
    @XmlElement
    private String phoneNumber;
    @Column(name = "email")
    @XmlElement
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name = "birthdate")
    @XmlElement
    private String birthdate;
    @Column(name = "date")
    @DateTimeFormat(pattern = "dd.mm.yyyy")
    @XmlElement
    private LocalDateTime date;


    @PrePersist
    public void dateNow() {
        this.date = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
