package com.example.http.entity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
@Data // getter setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tb_cards")
public class Cards {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "balance")
    private Double balance;
    @Column(name = "account")
    private String account;
    @Column(name = "number")
    private int number;
    @Column(name = "currency")
    private String currency;
    @Column(name = "main")
    private boolean main;
    @Column(name = "date")
    @DateTimeFormat(pattern = "dd.mm.yyyy")
    private LocalDateTime date;


    @PrePersist
    public void dateNow() {
        this.date = LocalDateTime.now();
    }


}
