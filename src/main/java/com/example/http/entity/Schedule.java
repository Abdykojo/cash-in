package com.example.http.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_schedule")
public class Schedule {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "creditId_id")
    private Long creditId;
    @Column(name = "firstBalance")
    private double firstBalance;
    @Column(name = "payment")
    private double payment;
    @Column(name = "finalBalance")
    private double finalBalance;
    @Column(name = "currency")
    private String currency;
    @Column(name = "date")
    @DateTimeFormat(pattern = "dd.mm.yyyy")
    private LocalDateTime date;

}
