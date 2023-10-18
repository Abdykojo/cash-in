package com.example.http.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import java.util.UUID;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_check")
public class Check {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Field(type = FieldType.Text)
    @Column(name = "currency")
    private String currency;
    @Column(name = "qid")
    private UUID qid;
    @Column (name = "account")
    private String account;
    @Column (name = "sender")
    private String sender;
    @Column(name = "date")
    private String date; //LocalDatetime
    @Column(name = "status")
    private String status;
    @Column(name = "type")
    private String type;

}
