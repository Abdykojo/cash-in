package com.example.http.repository;

import com.example.http.entity.Credit;
import com.example.http.entity.User;
import com.example.http.request.CreditRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditRepository extends JpaRepository<Credit, Long> {

    Credit getById (Long id);

    List<Credit> findCreditByAccount (String account);

}
